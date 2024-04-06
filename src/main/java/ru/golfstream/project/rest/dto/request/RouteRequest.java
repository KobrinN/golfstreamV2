package ru.golfstream.project.rest.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RouteRequest {
    private String fromWhere;
    private String toWhere;
    private String transportation;
    private LocalDate arrival;
    private LocalDate departure;
    private Long idInstructor;
}
