package ru.golfstream.project.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PurchaseDto {
    private Integer idVoucher;
    private Integer idClient;
    private LocalDate dateOfPurchase;
}
