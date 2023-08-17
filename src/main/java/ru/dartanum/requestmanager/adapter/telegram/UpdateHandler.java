package ru.dartanum.requestmanager.adapter.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dartanum.requestmanager.app.api.repository.RequestRepository;
import ru.dartanum.requestmanager.domain.Request;
import ru.dartanum.requestmanager.domain.RequestStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.dartanum.requestmanager.adapter.telegram.RequestActionConstants.*;

@Component
@RequiredArgsConstructor
public class UpdateHandler {
    private final RequestRepository requestRepository;
    private final BotProperties botProperties;

    public List<BotApiMethod> handleCallbackQuery(Update update) {
        var callback = update.getCallbackQuery();
        Request processedRequest = requestRepository.findByMessageId(callback.getMessage().getMessageId()).orElseThrow();
        String action = callback.getData();
        RequestStatus newStatus = selectNewStatus(action);

        if (processedRequest.getStatus().isNextStatus(newStatus)) {
            return handleNewStatus(callback, processedRequest, newStatus);
        }
        return Collections.emptyList();
    }

    private RequestStatus selectNewStatus(String action) {
        return switch (action) {
            case ACT_ACCEPT -> RequestStatus.IN_PROGRESS;
            case ACT_REJECT -> RequestStatus.REJECTED;
            case ACT_DENY -> RequestStatus.OPEN;
            default -> RequestStatus.CLOSE;
        };
    }

    private List<BotApiMethod> handleNewStatus(CallbackQuery callbackQuery, Request request, RequestStatus newStatus) {
        request.setStatus(newStatus);
        List<BotApiMethod> result = new ArrayList<>();

        EditMessageText editTextMethod = new EditMessageText();
        editTextMethod.setMessageId(request.getMessageId());
        editTextMethod.setChatId(botProperties.getChatId());
        result.add(editTextMethod);

        EditMessageReplyMarkup editKeyboardMethod = new EditMessageReplyMarkup();
        editKeyboardMethod.setMessageId(request.getMessageId());
        editKeyboardMethod.setChatId(botProperties.getChatId());
        result.add(editKeyboardMethod);

        switch (newStatus) {
            case OPEN -> {
                request.setExecutor(null);
                editTextMethod.setText(MessageFactory.openRequestMsg(request));
                editKeyboardMethod.setReplyMarkup(KeyboardFactory.acceptOrRejectKeyboard());
            }
            case IN_PROGRESS -> {
                request.setExecutor(callbackQuery.getFrom().getUserName());
                editTextMethod.setText(MessageFactory.requestWithExecutorMsg(request));
                editKeyboardMethod.setReplyMarkup(KeyboardFactory.closeOrDenyKeyboard());
            }
            case CLOSE -> {
                if (!callbackQuery.getFrom().getUserName().equals(request.getExecutor())) {
                    throw new NotRequestOwnerException(request.getId(), request.getExecutor(), callbackQuery.getFrom().getUserName());
                }

                editTextMethod.setText(MessageFactory.requestWithExecutorMsg(request));
                editKeyboardMethod.setReplyMarkup(null);
            }
            case REJECTED -> {
                request.setExecutor(callbackQuery.getFrom().getUserName());
                editTextMethod.setText(MessageFactory.rejectedRequestMsg(request));
                editKeyboardMethod.setReplyMarkup(null);
            }
        }

        requestRepository.save(request);
        return result;
    }
}
