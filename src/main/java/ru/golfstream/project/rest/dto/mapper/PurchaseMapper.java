package ru.golfstream.project.rest.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.golfstream.project.entity.Purchase;
import ru.golfstream.project.rest.dto.response.PurchaseResponse;

@Mapper
public interface PurchaseMapper {
    @Mapping(target = "idUser", source = "purchase.user.id")
    @Mapping(target = "idVoucher", source = "purchase.voucher.id")
    PurchaseResponse toResponse(Purchase purchase);
}
