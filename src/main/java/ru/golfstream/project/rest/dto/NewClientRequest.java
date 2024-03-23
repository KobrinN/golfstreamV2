package ru.golfstream.project.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewClientRequest {
    String name;
    String surname;
    String secondName;
    String mail;
    String password;
}
