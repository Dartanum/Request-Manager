package ru.dartanum.requestmanager.app.api;

import ru.dartanum.requestmanager.domain.Request;

public interface CreateRequestInbound {
    void execute(Request request);
}
