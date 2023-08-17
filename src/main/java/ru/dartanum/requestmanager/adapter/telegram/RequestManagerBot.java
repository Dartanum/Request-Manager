package ru.dartanum.requestmanager.adapter.telegram;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Slf4j
public class RequestManagerBot extends TelegramLongPollingBot {
    private final BotProperties botProperties;
    private final UpdateHandler updateHandler;

    protected RequestManagerBot(BotProperties botProperties, UpdateHandler updateHandler) {
        super(botProperties.getToken());
        this.botProperties = botProperties;
        this.updateHandler = updateHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        List<BotApiMethod> methods = new ArrayList<>();
        if (update.hasCallbackQuery()) {
            methods = updateHandler.handleCallbackQuery(update);
        }
        try {
            for (var method : methods) {

                execute(method);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botProperties.getUsername();
    }

    public Message sendMessage(SendMessage method) {
        try {
            method.setChatId(botProperties.getChatId());
            return execute(method);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostConstruct
    public void register() {
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(this);
        } catch (TelegramApiException e) {
            log.error("Error during register bot");
        }
    }
}
