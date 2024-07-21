package ru.golfstream.project.rest.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.golfstream.project.entity.Voucher;
import ru.golfstream.project.rest.dto.request.VoucherRequest;
import ru.golfstream.project.rest.dto.response.VoucherResponse;

@Mapper
public interface VoucherMapper {
    @Mapping(target = "route.id", source = "request.idRoute")
    Voucher toModel(VoucherRequest request);
    VoucherRequest toRequest(Voucher voucher);
    VoucherResponse toResponse(Voucher voucher);
    Voucher toModel(VoucherResponse response);
}
