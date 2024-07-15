package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.User;
import ru.golfstream.project.entity.Purchase;
import ru.golfstream.project.entity.Voucher;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.exception.exceptions.voucher.FillVoucherException;
import ru.golfstream.project.repos.ClientRepo;
import ru.golfstream.project.repos.PurchaseRepo;
import ru.golfstream.project.repos.VoucherRepo;
import ru.golfstream.project.rest.dto.PurchaseDto;
import ru.golfstream.project.service.PurchaseService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    final PurchaseRepo purchaseRepo;
    private final VoucherRepo voucherRepo;
    private final ClientRepo clientRepo;

    @Override
    public List<PurchaseDto> findPurchaseOfVoucher(Long id) {
        Optional<Voucher> voucherFromDb = voucherRepo.findById(id);
        if(voucherFromDb.isEmpty()){
            throw new NotFoundException("Нет путёвки с ID = " + id + "!");
        }
        List<Purchase> purchases = purchaseRepo.findByIdVoucher(voucherFromDb.get());
        return purchases.stream()
                .map(PurchaseServiceImpl::buildPurchaseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseDto buy(Long idClient, Long idVoucher) {
        Optional<User> clientFromDb = clientRepo.findById(idClient);
        Optional<Voucher> voucherFromDb = voucherRepo.findById(idVoucher);
        if(clientFromDb.isEmpty()){
            throw new NotFoundException("Нет клиента с ID = " + idClient + "!");
        }
        if(voucherFromDb.isEmpty()){
            throw new NotFoundException("Нет путёвки с ID = " + idVoucher + "!");
        }

        Voucher voucher = voucherFromDb.get();
        if(voucher.getReservation().equals(voucher.getQuantity())){
            throw new FillVoucherException("Все путёвки с ID = " + idVoucher + " раскуплены!");
        }
        voucher.setReservation(voucher.getReservation() + 1L);
        voucherRepo.saveAndFlush(voucher);

        Purchase purchase = new Purchase();
        purchase.setIdClient(clientFromDb.get());
        purchase.setIdVoucher(voucherFromDb.get());
        purchase.setDateOfPurchase(LocalDate.now());
        purchaseRepo.saveAndFlush(purchase);

        return buildPurchaseDto(purchase);
    }

    @Override
    public void delete(Long id) {

    }

    protected static PurchaseDto buildPurchaseDto(Purchase purchase) {
        return PurchaseDto.builder()
                .idVoucher(purchase.getIdVoucher().getId())
                .idClient(purchase.getIdClient().getId())
                .dateOfPurchase(purchase.getDateOfPurchase())
                .build();
    }
}
