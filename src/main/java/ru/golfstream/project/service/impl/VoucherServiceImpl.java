package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.Purchase;
import ru.golfstream.project.entity.Route;
import ru.golfstream.project.entity.Voucher;
import ru.golfstream.project.exception.exceptions.common.InvlaidFieldException;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.PurchaseRepo;
import ru.golfstream.project.repos.RouteRepo;
import ru.golfstream.project.repos.VoucherRepo;
import ru.golfstream.project.rest.dto.NewOrUpdateVoucherRequest;
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
    private final RouteRepo routeRepo;

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
        if (voucherFromDb.isEmpty()) {
            throw new NotFoundException("нет такого тарифа!");
        }

        return buildVoucherDto(voucherFromDb.get());
    }

    @Override
    public List<PurchaseDto> findPurchaseOfVoucher(Integer id) {
        Optional<Voucher> voucherFromDb = voucherRepo.findById(id);
        if (voucherFromDb.isEmpty()) {
            throw new NotFoundException("нет такого тарифа!");
        }
        List<Purchase> purchases = purchaseRepo.findByIdVoucher(voucherFromDb.get());
        return purchases.stream()
                .map(PurchaseServiceImpl::buildPurchaseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer add(NewOrUpdateVoucherRequest request) {
        proofVoucherRequest(request);
        Optional<Route> routeFromDb = routeRepo.findById(request.getIdRoute());
        if(routeFromDb.isEmpty()){
            throw new NotFoundException("Нет маршрута с ID = " + request.getIdRoute() + "!");
        }
        Voucher voucher = new Voucher();

        voucher.setName(request.getName());
        voucher.setPrice(request.getPrice());
        voucher.setQuantity(request.getQuantity());
        voucher.setReservation(request.getReservation());
        voucher.setIdRoute(routeFromDb.get());

        voucherRepo.saveAndFlush(voucher);
        return voucher.getId();
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Voucher> voucherFromDb = voucherRepo.findById(id);
        if(voucherFromDb.isEmpty()){
            throw new NotFoundException("Нет путёвки с ID = " + id + "!");
        }

        voucherRepo.deleteById(id);
    }

    @Override
    public VoucherDto update(Integer id, NewOrUpdateVoucherRequest request) {
        proofVoucherRequest(request);
        Optional<Voucher> voucherFromDb = voucherRepo.findById(id);
        Optional<Route> routeFromDb = routeRepo.findById(request.getIdRoute());
        if(voucherFromDb.isEmpty()){
            throw new NotFoundException("Нет путёвки с ID = " + id + "!");
        }
        if(routeFromDb.isEmpty()){
            throw new NotFoundException("Нет маршрута с ID = " + request.getIdRoute() + "!");
        }
        Voucher voucher = new Voucher();
        voucher.setName(request.getName());
        voucher.setPrice(request.getPrice());
        voucher.setQuantity(request.getQuantity());
        voucher.setReservation(request.getReservation());
        voucher.setIdRoute(routeFromDb.get());



        return buildVoucherDto(voucher);
    }

    protected static VoucherDto buildVoucherDto(Voucher voucher) {
        return VoucherDto.builder()
                .name(voucher.getName())
                .reservation(voucher.getReservation())
                .price(voucher.getPrice())
                .quantity(voucher.getQuantity())
                .build();
    }

    protected void proofVoucherRequest(NewOrUpdateVoucherRequest request){
        if ((request.getReservation() <= 0) ||
                (request.getQuantity() <= 0) ||
                (request.getPrice() <= 0)) {
            throw new InvlaidFieldException("Некорректные значения путёвки!");
        }
        Optional<Route> routeFromDb = routeRepo.findById(request.getIdRoute());
        if(routeFromDb.isEmpty()){
            throw new NotFoundException("Нет такого маршрута!");
        }
    }

}
