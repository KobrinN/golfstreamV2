package ru.golfstream.project.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PurchaseDto {
    private Long idVoucher;
    private Long idClient;
    private LocalDate dateOfPurchase;
}
