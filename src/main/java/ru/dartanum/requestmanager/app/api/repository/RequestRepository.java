package ru.dartanum.requestmanager.app.api.repository;

import ru.dartanum.requestmanager.domain.Request;

import java.util.Optional;

public interface RequestRepository {
    void save(Request request);

    Optional<Request> findByMessageId(long messageId);
}
