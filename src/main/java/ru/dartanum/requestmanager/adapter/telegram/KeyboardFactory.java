package ru.dartanum.requestmanager.adapter.telegram;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class KeyboardFactory {
    public static InlineKeyboardMarkup acceptOrRejectKeyboard() {
        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("Принять").callbackData(RequestActionConstants.ACT_ACCEPT).build(),
                        InlineKeyboardButton.builder().text("Отклонить").callbackData(RequestActionConstants.ACT_REJECT).build()
                ))
                .build();
    }

    public static InlineKeyboardMarkup closeOrDenyKeyboard() {
        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("Закрыть").callbackData(RequestActionConstants.ACT_CLOSE).build(),
                        InlineKeyboardButton.builder().text("Отказаться").callbackData(RequestActionConstants.ACT_DENY).build()
                ))
                .build();
    }
}
