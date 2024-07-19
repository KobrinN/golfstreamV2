package ru.golfstream.project.rest.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.engine.spi.ManagedEntity;

@Data
public class VoucherRequest {
    @NotBlank(message = "Not valid NAME!")
    private String name;
    @NotNull(message = "Not valid PRICE!")
    @Min(value = 0)
    private Double price;
    @NotNull
    @Min(value = 1, message = "QUANTITY must be more then 0!")
    private Long quantity;
    @NotNull
    @Min(value = 0, message = "Not valid RESERVATION!")
    private Long reservation;
    @NotNull(message = "Not valid route-ID!")
    @Min(value = 1, message = "route-ID must be more then 0!")
    private Long idRoute;
}
