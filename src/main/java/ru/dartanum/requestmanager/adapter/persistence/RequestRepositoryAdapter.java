package ru.dartanum.requestmanager.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.dartanum.requestmanager.app.api.repository.RequestRepository;
import ru.dartanum.requestmanager.domain.Request;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RequestRepositoryAdapter implements RequestRepository {
    private final RequestJpaRepository requestJpaRepository;

    @Override
    public void save(Request request) {
        requestJpaRepository.save(request);
    }

    @Override
    public Optional<Request> findByMessageId(long messageId) {
        return requestJpaRepository.findByMessageId(messageId);
    }
}
