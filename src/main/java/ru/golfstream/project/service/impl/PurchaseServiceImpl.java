package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.Purchase;
import ru.golfstream.project.repos.PurchaseRepo;
import ru.golfstream.project.rest.dto.PurchaseDto;
import ru.golfstream.project.service.PurchaseService;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    final PurchaseRepo purchaseRepo;

    protected static PurchaseDto buildPurchaseDto(Purchase purchase) {
        return PurchaseDto.builder()
                .idVoucher(purchase.getIdVoucher().getId())
                .idClient(purchase.getIdClient().getId())
                .dateOfPurchase(purchase.getDateOfPurchase())
                .build();
    }
}
