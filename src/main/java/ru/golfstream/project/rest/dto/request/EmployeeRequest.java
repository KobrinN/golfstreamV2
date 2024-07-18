package ru.golfstream.project.rest.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class EmployeeRequest {
    private String name;
    private String surname;
    private String secondName;
    private String country;
    private Long openingHours;
    private Long idType;
}
