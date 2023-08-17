package ru.dartanum.requestmanager.adapter.rest;

import org.springframework.stereotype.Component;
import ru.dartanum.requestmanager.adapter.rest.dto.RequestDto;
import ru.dartanum.requestmanager.domain.Request;
import ru.dartanum.requestmanager.domain.RequestStatus;

@Component
public class RequestMapper {

    public Request map(RequestDto dto) {
        return new Request(null, dto.getRequestReason(), RequestStatus.OPEN, dto.getFullName(), dto.getPhoneNumber(), dto.getEmail(), dto.getComment(), 0, null);
    }
}
