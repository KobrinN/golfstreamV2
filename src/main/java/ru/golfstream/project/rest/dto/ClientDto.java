package ru.golfstream.project.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ClientDto {
    private String name;
    private String surname;
    private String secondName;
    private String mail;
}
