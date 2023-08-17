package ru.dartanum.requestmanager.adapter.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dartanum.requestmanager.domain.Request;

import java.util.Optional;

public interface RequestJpaRepository extends MongoRepository<Request, String> {
    Optional<Request> findByMessageId(long messageId);
}
