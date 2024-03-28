package ru.golfstream.project.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewOrUpdateEmployeeRequest {
    private String name;
    private String surname;
    private String secondName;
    private String country;
    private Integer openingHours;
    private Integer typeId;
}
