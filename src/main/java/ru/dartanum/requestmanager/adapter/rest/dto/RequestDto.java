package ru.dartanum.requestmanager.adapter.rest.dto;

import lombok.Data;
import ru.dartanum.requestmanager.domain.RequestReason;

@Data
public class RequestDto {
    private RequestReason requestReason;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String comment;
}
