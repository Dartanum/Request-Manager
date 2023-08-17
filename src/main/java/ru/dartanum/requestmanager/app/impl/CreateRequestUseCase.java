package ru.dartanum.requestmanager.app.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.dartanum.requestmanager.app.api.CreateRequestInbound;
import ru.dartanum.requestmanager.app.api.SendCreateRequestMessageOutbound;
import ru.dartanum.requestmanager.app.api.repository.RequestRepository;
import ru.dartanum.requestmanager.domain.Request;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class CreateRequestUseCase implements CreateRequestInbound {
    private final SendCreateRequestMessageOutbound sendCreateRequestMessageOutbound;
    private final RequestRepository requestRepository;

    @Override
    public void execute(Request request) {
        request.setId(generateRequestId());
        Request sentRequest = sendCreateRequestMessageOutbound.execute(request);
        requestRepository.save(sentRequest);
    }

    private String generateRequestId() {
        Random random = new Random();
        return "RQ" + random.nextInt(1000000, 10000000);
    }
}
