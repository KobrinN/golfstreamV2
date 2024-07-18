package ru.golfstream.project.rest.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class TypeEmployeeRequest {
    private Double rate;
    private String type;
}
