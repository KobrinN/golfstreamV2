package ru.golfstream.project.rest.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class TypeEmployeeResponse {
    private Long id;
    private Double rate;
    private String type;
}
