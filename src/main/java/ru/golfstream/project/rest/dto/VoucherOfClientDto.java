package ru.golfstream.project.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoucherOfClientDto{
    private String nameOfVoucher;
    private Double price;
}
