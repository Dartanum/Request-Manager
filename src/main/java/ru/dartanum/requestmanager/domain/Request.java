package ru.dartanum.requestmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    private String id;
    private RequestReason requestReason;
    private RequestStatus status;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String comment;
    private int messageId;
    private String executor;
}
