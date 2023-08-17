package ru.dartanum.requestmanager.adapter.telegram;

import ru.dartanum.requestmanager.domain.Request;

public class MessageFactory {
    private static final String REQUEST_MSG_TEMPLATE = """
            Заявка №%s (%s)
            - Причина обращения: %s
            - Клиент: %s
            - Телефон: %s
            - Почта: %s
            - Комментарий: %s
            """;
    private static final String REQUEST_WITH_EXECUTOR_MSG_TEMPLATE = """
            Заявка №%s (%s)
            - Причина обращения: %s
            - Клиент: %s
            - Телефон: %s
            - Почта: %s
            - Комментарий: %s
            
            Исполнитель: %s
            """;
    private static final String REJECTED_REQUEST_MSG_TEMPLATE = """
            Заявка №%s (%s)
            - Причина обращения: %s
            - Клиент: %s
            - Телефон: %s
            - Почта: %s
            - Комментарий: %s
            
            Отклонивший: %s
            """;

    public static String openRequestMsg(Request request) {
        return String.format(REQUEST_MSG_TEMPLATE, request.getId(), request.getStatus().getDisplayName(), request.getRequestReason(), request.getFullName(),
                request.getPhoneNumber(), request.getEmail(), request.getComment());
    }

    public static String requestWithExecutorMsg(Request request) {
        return String.format(REQUEST_WITH_EXECUTOR_MSG_TEMPLATE, request.getId(), request.getStatus().getDisplayName(), request.getRequestReason(), request.getFullName(),
                request.getPhoneNumber(), request.getEmail(), request.getComment(), "@" + request.getExecutor());
    }

    public static String rejectedRequestMsg(Request request) {
        return String.format(REJECTED_REQUEST_MSG_TEMPLATE, request.getId(), request.getStatus().getDisplayName(), request.getRequestReason(), request.getFullName(),
                request.getPhoneNumber(), request.getEmail(), request.getComment(), "@" + request.getExecutor());
    }
}
