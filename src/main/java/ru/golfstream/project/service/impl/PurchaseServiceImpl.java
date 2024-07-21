package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.User;
import ru.golfstream.project.entity.Purchase;
import ru.golfstream.project.entity.Voucher;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.exception.exceptions.voucher.FillVoucherException;
import ru.golfstream.project.repos.UserRepo;
import ru.golfstream.project.repos.PurchaseRepo;
import ru.golfstream.project.repos.VoucherRepo;
import ru.golfstream.project.rest.dto.mapper.PurchaseMapper;
import ru.golfstream.project.rest.dto.mapper.PurchaseMapperImpl;
import ru.golfstream.project.rest.dto.response.PurchaseResponse;
import ru.golfstream.project.service.PurchaseService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    final PurchaseRepo purchaseRepo;
    private final VoucherRepo voucherRepo;
    private final UserRepo userRepo;
    private final PurchaseMapper purchaseMapper = new PurchaseMapperImpl();

    @Override
    public List<PurchaseResponse> getPurchaseOfVoucher(Long id) {
        Optional<Voucher> voucher = voucherRepo.findById(id);
        if(voucher.isEmpty()) throw new NotFoundException("Not found VOUCHER with id: " + id + "!");
        List<Purchase> purchases = purchaseRepo.findByVoucher(voucher.get());
        return purchases.stream()
                .map(purchaseMapper::toResponse)
                .toList();
    }

    @Override
    public PurchaseResponse buy(Long idUser, Long idVoucher) {
        Optional<User> clientFromDb = userRepo.findById(idUser);
        Optional<Voucher> voucherFromDb = voucherRepo.findById(idVoucher);
        if(clientFromDb.isEmpty()){
            throw new NotFoundException("Not found USER with id: " + idUser + "!");
        }
        if(voucherFromDb.isEmpty()){
            throw new NotFoundException("Not found VOUCHER with id: " + idVoucher + "!");
        }
        Voucher voucher = voucherFromDb.get();
        if(voucher.getReservation() >= (voucher.getQuantity())){
            throw new FillVoucherException("Every VOUCHER with id: " + idVoucher + " sold!");
        }
        voucher.setReservation(voucher.getReservation() + 1L);
        voucherRepo.saveAndFlush(voucher);

        Purchase purchase = new Purchase();
        purchase.setUser(clientFromDb.get());
        purchase.setVoucher(voucherFromDb.get());
        purchase.setDateOfPurchase(LocalDate.now());
        purchaseRepo.saveAndFlush(purchase);

        return purchaseMapper.toResponse(purchase);
    }

    @Override
    public void delete(Long id) {
        Optional<Purchase> purchase = purchaseRepo.findById(id);
        purchase.ifPresent(purchaseRepo::delete);
    }

    private Purchase checkExistAndGetPurchaseById(Long id){
        Optional<Purchase> purchase = purchaseRepo.findById(id);
        if(purchase.isEmpty()) throw new NotFoundException("Not found PURCHASE with id: " + id + "!");
        return purchase.get();
    }
}
