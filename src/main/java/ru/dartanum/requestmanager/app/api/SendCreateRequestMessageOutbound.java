package ru.dartanum.requestmanager.app.api;

import ru.dartanum.requestmanager.domain.Request;

public interface SendCreateRequestMessageOutbound {
    Request execute(Request request);
}
