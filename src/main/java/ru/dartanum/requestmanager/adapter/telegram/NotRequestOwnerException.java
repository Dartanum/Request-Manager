package ru.dartanum.requestmanager.adapter.telegram;

import java.io.Serial;

import static java.lang.String.format;

public class NotRequestOwnerException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -4767898893093828763L;
    private static final String MESSAGE_TEMPLATE = "Попытка изменить статус заявки %s, находящейся в работе у пользователя %s, пользователем %s";

    public NotRequestOwnerException(String requestId, String ownerUsername, String executorUsername) {
        super(format(MESSAGE_TEMPLATE, requestId, ownerUsername, executorUsername));
    }
}
