package ru.golfstream.project.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {
    private String name;
    private String surname;
    private String secondName;
    private String country;
    private Integer openingHours;
}
