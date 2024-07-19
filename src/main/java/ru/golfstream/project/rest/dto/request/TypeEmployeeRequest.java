package ru.golfstream.project.rest.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class TypeEmployeeRequest {
    @NotNull(message = "Not valid RATE!")
    @Min(value = 0, message = "RATE must be more or equal to 0!")
    private Double rate;
    @NotBlank(message = "Not valid TYPE!")
    private String type;
}
