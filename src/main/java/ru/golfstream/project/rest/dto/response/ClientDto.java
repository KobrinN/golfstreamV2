package ru.golfstream.project.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ClientDto {
    private String username;
    private String mail;
}
