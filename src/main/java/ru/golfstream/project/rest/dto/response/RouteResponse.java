package ru.golfstream.project.rest.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RouteResponse {
    private Long id;
    private String fromWhere;
    private String toWhere;
    private String transportation;
    private LocalDate arrival;
    private LocalDate departure;
}
