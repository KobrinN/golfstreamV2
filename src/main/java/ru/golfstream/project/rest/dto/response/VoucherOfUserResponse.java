package ru.golfstream.project.rest.dto.response;

import lombok.*;

@Data
public class VoucherOfUserResponse {
    private Long id;
    private String name;
    private Double price;
}
