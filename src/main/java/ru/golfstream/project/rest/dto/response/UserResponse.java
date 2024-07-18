package ru.golfstream.project.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String mail;
}
