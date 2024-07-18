package ru.golfstream.project.rest.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class UserRequest {
    String username;
    String password;
    String mail;
}
