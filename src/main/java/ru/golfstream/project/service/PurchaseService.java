package ru.golfstream.project.service;

import ru.golfstream.project.rest.dto.response.PurchaseDto;

import java.util.List;

public interface PurchaseService {
    List<PurchaseDto> findPurchaseOfVoucher(Long id);

    PurchaseDto buy(Long idClient, Long idVoucher);

    void delete(Long id);
}