package ru.golfstream.project.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeEmployeeDto {
    private Double rate;
    private String type;
}
