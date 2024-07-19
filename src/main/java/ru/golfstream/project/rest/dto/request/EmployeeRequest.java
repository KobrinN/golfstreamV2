package ru.golfstream.project.rest.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class EmployeeRequest {
    @NotBlank(message = "Not valid NAME!")
    private String name;
    @NotBlank(message = "Not valid SURNAME!")
    private String surname;
    @NotBlank(message = "Not valid SECOND_NAME!")
    private String secondName;
    @NotBlank(message = "Not valid COUNTRY!")
    private String country;
    @NotNull(message = "Not valid OPENING_HOURS!")
    @Min(value = 0, message = "OPENING_HOURS must be more or equal 0!")
    private Long openingHours;
    @NotNull(message = "Not valid type-ID!")
    @Min(value = 1, message = "Type-ID must be more then 0!")
    private Long idType;
}
