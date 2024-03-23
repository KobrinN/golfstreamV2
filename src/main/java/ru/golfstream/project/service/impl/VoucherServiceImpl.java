package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.Purchase;
import ru.golfstream.project.entity.Route;
import ru.golfstream.project.entity.Voucher;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.PurchaseRepo;
import ru.golfstream.project.repos.VoucherRepo;
import ru.golfstream.project.rest.dto.PurchaseDto;
import ru.golfstream.project.rest.dto.VoucherDto;
import ru.golfstream.project.service.VoucherService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {
    private final VoucherRepo voucherRepo;
    private final PurchaseRepo purchaseRepo;
    @Override
    public List<Voucher> getByRouteId(Integer id) {
        return voucherRepo.findByRouteId(id);
    }

    @Override
    public List<VoucherDto> findAll() {
        List<Voucher> vouchers = voucherRepo.findAll();
        return vouchers.stream()
                .map(VoucherServiceImpl::buildVoucherDto)
                .collect(Collectors.toList());
    }

    @Override
    public VoucherDto findById(Integer id) {
        Optional<Voucher> voucherFromDb = voucherRepo.findById(id);
        if(voucherFromDb.isEmpty()){
            throw new NotFoundException("нет такого тарифа!");
        }

        return buildVoucherDto(voucherFromDb.get());
    }

    @Override
    public List<PurchaseDto> findPurchaseOfVoucher(Integer id) {
        Optional<Voucher> voucherFromDb = voucherRepo.findById(id);
        if(voucherFromDb.isEmpty()){
            throw new NotFoundException("нет такого тарифа!");
        }
        List<Purchase> purchases = purchaseRepo.findByIdVoucher(voucherFromDb.get());
        return purchases.stream()
                .map(PurchaseServiceImpl::buildPurchaseDto)
                .collect(Collectors.toList());
    }

    protected static VoucherDto buildVoucherDto(Voucher voucher){
        return VoucherDto.builder()
                .name(voucher.getName())
                .reservation(voucher.getReservation())
                .price(voucher.getPrice())
                .quantity(voucher.getQuantity())
                .build();
    }

}
