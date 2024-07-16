package ru.golfstream.project.rest.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoucherDto {
    private String name;
    private Double price;
    private Long quantity;
    private Long reservation;
}
