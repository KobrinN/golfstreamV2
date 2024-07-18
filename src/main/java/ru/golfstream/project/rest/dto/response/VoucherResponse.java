package ru.golfstream.project.rest.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class VoucherResponse {
    private Long id;
    private String name;
    private Double price;
    private Long quantity;
    private Long reservation;
}
