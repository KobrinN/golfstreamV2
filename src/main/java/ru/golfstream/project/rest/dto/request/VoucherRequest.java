package ru.golfstream.project.rest.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class VoucherRequest {
    private String name;
    private Double price;
    private Long quantity;
    private Long reservation;
    private Long idRoute;
}
