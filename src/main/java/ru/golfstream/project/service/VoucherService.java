package ru.golfstream.project.service;

import ru.golfstream.project.entity.Route;
import ru.golfstream.project.entity.Voucher;
import ru.golfstream.project.rest.dto.PurchaseDto;
import ru.golfstream.project.rest.dto.VoucherDto;

import java.util.List;

public interface VoucherService {
    List<Voucher> getByRouteId(Integer id);

    List<VoucherDto> findAll();

    VoucherDto findById(Integer id);

    List<PurchaseDto> findPurchaseOfVoucher(Integer id);
}
