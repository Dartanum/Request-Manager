package ru.dartanum.requestmanager.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dartanum.requestmanager.adapter.rest.dto.RequestDto;
import ru.dartanum.requestmanager.app.api.CreateRequestInbound;
import ru.dartanum.requestmanager.domain.Request;

@RestController
@RequiredArgsConstructor
public class RequestController {
    private final RequestMapper requestMapper;
    private final CreateRequestInbound createRequestInbound;

    @PutMapping("/request")
    public ResponseEntity<?> createRequest(@RequestBody RequestDto dto) {
        Request request = requestMapper.map(dto);
        createRequestInbound.execute(request);

        return ResponseEntity.ok().build();
    }
}
