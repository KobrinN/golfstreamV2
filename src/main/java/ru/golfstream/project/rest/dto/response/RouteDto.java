package ru.golfstream.project.rest.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RouteDto {
    private String fromWhere;
    private String toWhere;
    private String transportation;
    private LocalDate arrival;
    private LocalDate departure;
}
