package ru.golfstream.project.rest.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PurchaseResponse {
    private Long id;
    private Long idVoucher;
    private Long idUser;
    private LocalDate dateOfPurchase;
}
