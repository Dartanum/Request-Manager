package ru.dartanum.requestmanager.adapter.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dartanum.requestmanager.app.api.SendCreateRequestMessageOutbound;
import ru.dartanum.requestmanager.domain.Request;

@Component
@RequiredArgsConstructor
public class SendCreateRequestMessageAdapter implements SendCreateRequestMessageOutbound {
    private final RequestManagerBot bot;

    @Override
    public Request execute(Request request) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(MessageFactory.openRequestMsg(request));
        sendMessage.setReplyMarkup(KeyboardFactory.acceptOrRejectKeyboard());

        Message sentMessage = bot.sendMessage(sendMessage);
        request.setMessageId(sentMessage.getMessageId());

        return request;
    }
}
