package ru.golfstream.project.service;

import ru.golfstream.project.entity.Voucher;
import ru.golfstream.project.rest.dto.request.VoucherRequest;
import ru.golfstream.project.rest.dto.response.VoucherDto;

import java.util.List;

public interface VoucherService {
    List<Voucher> getByRouteId(Long id);

    List<VoucherDto> findAll();

    VoucherDto findById(Long id);

    List<VoucherDto> getVouchersByRouteId(Long id);

    Long add(VoucherRequest request);

    void deleteById(Long id);

    VoucherDto update(Long id, VoucherRequest request);

    List<VoucherDto> findVouchersOfClient(Long id);
}
