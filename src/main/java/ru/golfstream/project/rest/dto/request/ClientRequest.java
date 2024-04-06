package ru.golfstream.project.rest.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientRequest {
    String name;
    String surname;
    String secondName;
    String mail;
    String password;
}
