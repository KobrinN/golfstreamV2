package ru.golfstream.project.rest.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RouteRequest {
    @NotBlank(message = "Not valid PLACE OF DEPARTURE!")
    private String fromWhere;
    @NotBlank(message = "Not valid PLACE OF ARRIVAL!")
    private String toWhere;
    @NotBlank(message = "Not valid KIND OF TRANSPORTATION!")
    private String transportation;
    @Future(message = "ARRIVAL TIME should be later than now!")
    private LocalDate arrival;
    @FutureOrPresent(message = "DEPARTURE TIME should be later or exactly now!")
    private LocalDate departure;
    @NotNull(message = "Not valid instructor-ID!")
    @Min(value = 1, message = "Instructor-ID must be more then 0!")
    private Long idInstructor;
}
