package ru.golfstream.project.service;

import ru.golfstream.project.entity.Voucher;
import ru.golfstream.project.rest.dto.request.VoucherRequest;
import ru.golfstream.project.rest.dto.response.VoucherResponse;

import java.util.List;

public interface VoucherService extends Service <VoucherRequest, VoucherResponse>{
    List<Voucher> getByRouteId(Long id);

    List<VoucherResponse> getVouchersByRouteId(Long id);

    List<VoucherResponse> findVouchersByUserId(Long id);
}
