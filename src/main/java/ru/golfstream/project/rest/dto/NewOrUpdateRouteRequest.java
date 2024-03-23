package ru.golfstream.project.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class NewOrUpdateRouteRequest {
    private String fromWhere;
    private String toWhere;
    private String transportation;
    private LocalDate arrival;
    private LocalDate departure;
    private Integer idInstructor;
}
