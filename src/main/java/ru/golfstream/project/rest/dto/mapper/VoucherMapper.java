package ru.golfstream.project.rest.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.golfstream.project.entity.Voucher;
import ru.golfstream.project.rest.dto.request.VoucherRequest;
import ru.golfstream.project.rest.dto.response.VoucherResponse;

@Mapper
public interface VoucherMapper {
    @Mapping(target = "route.id", source = "request.idRoute")
    Voucher toModel(VoucherRequest request);
    VoucherResponse toResponse(Voucher voucher);
}
