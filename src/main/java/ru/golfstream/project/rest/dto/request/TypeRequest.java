package ru.golfstream.project.rest.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeRequest {
    private Double rate;
    private String type;
}
