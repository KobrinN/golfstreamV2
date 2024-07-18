package ru.golfstream.project.service;

import ru.golfstream.project.rest.dto.response.PurchaseResponse;

import java.util.List;

public interface PurchaseService {
    List<PurchaseResponse> getPurchaseOfVoucher(Long id);

    PurchaseResponse buy(Long idClient, Long idVoucher);

    void delete(Long id);
}