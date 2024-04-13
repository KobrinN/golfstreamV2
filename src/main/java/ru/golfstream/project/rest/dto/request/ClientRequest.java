package ru.golfstream.project.rest.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientRequest {
    String username;
    String mail;
    String password;
}
