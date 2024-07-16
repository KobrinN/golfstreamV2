package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.User;
import ru.golfstream.project.entity.Route;
import ru.golfstream.project.entity.Voucher;
import ru.golfstream.project.exception.exceptions.client.NotFoundVoucherOfThisClient;
import ru.golfstream.project.exception.exceptions.common.InvlaidFieldException;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.UserRepo;
import ru.golfstream.project.repos.PurchaseRepo;
import ru.golfstream.project.repos.RouteRepo;
import ru.golfstream.project.repos.VoucherRepo;
import ru.golfstream.project.rest.dto.request.VoucherRequest;
import ru.golfstream.project.rest.dto.response.VoucherDto;
import ru.golfstream.project.service.VoucherService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {
    private final UserRepo clientRepo;
    private final VoucherRepo voucherRepo;
    private final PurchaseRepo purchaseRepo;
    private final RouteRepo routeRepo;

    @Override
    public List<Voucher> getByRouteId(Long id) {
        return voucherRepo.findByRoute(routeRepo.findById(id).get());
    }

    @Override
    public List<VoucherDto> findAll() {
        List<Voucher> vouchers = voucherRepo.findAll();
        return vouchers.stream()
                .map(VoucherServiceImpl::buildVoucherDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VoucherDto> getVouchersByRouteId(Long id) {
        Optional<Route> routeFromDb = routeRepo.findById(id);

        if(routeFromDb.isEmpty()){
            throw new NotFoundException("Нет маршрута с id = " + id + "!");
        }

        List<Voucher> voucherFromDb = voucherRepo.findByRoute(routeFromDb.get());
        return voucherFromDb.stream()
                .map(VoucherServiceImpl::buildVoucherDto)
                .collect(Collectors.toList());
    }

    @Override
    public VoucherDto findById(Long id) {
        Optional<Voucher> voucherFromDb = voucherRepo.findById(id);
        if (voucherFromDb.isEmpty()) {
            throw new NotFoundException("нет такого тарифа!");
        }

        return buildVoucherDto(voucherFromDb.get());
    }

    @Override
    public Long add(VoucherRequest request) {
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
        voucher.setRoute(routeFromDb.get());

        voucherRepo.saveAndFlush(voucher);
        return voucher.getId();
    }

    @Override
    public void deleteById(Long id) {
        Optional<Voucher> voucherFromDb = voucherRepo.findById(id);
        if(voucherFromDb.isEmpty()){
            throw new NotFoundException("Нет путёвки с ID = " + id + "!");
        }

        voucherRepo.deleteById(id);
    }

    @Override
    public VoucherDto update(Long id, VoucherRequest request) {
        proofVoucherRequest(request);
        Optional<Voucher> voucherFromDb = voucherRepo.findById(id);
        Optional<Route> routeFromDb = routeRepo.findById((request.getIdRoute()));
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
        voucher.setRoute(routeFromDb.get());



        return buildVoucherDto(voucher);
    }

    @Override
    public List<VoucherDto> findVouchersOfClient(Long id) {
        Optional<User> clientFromBd = clientRepo.findById(id);
        if(clientFromBd.isEmpty()){
            throw new NotFoundException("Нет пользователя с ID = "+ id + "!");
        }
        List<Voucher> clientAndVoucherDtoList = clientRepo.getVoucherOfClientDto(id);

        if (clientAndVoucherDtoList.isEmpty()) {
            throw new NotFoundVoucherOfThisClient("Не найдены путёвки этого пользователя!");
        }

        return clientAndVoucherDtoList.stream()
                .map(VoucherServiceImpl::buildVoucherDto)
                .collect(Collectors.toList());
    }

    protected static VoucherDto buildVoucherDto(Voucher voucher) {
        return VoucherDto.builder()
                .name(voucher.getName())
                .reservation(voucher.getReservation())
                .price(voucher.getPrice())
                .quantity(voucher.getQuantity())
                .build();
    }

    protected void proofVoucherRequest(VoucherRequest request){
        if ((request.getReservation() <= 0) ||
                (request.getQuantity() <= 0) ||
                (request.getPrice() <= 0)) {
            throw new InvlaidFieldException("Некорректные значения путёвки!");
        }
        Optional<Route> routeFromDb = routeRepo.findById((request.getIdRoute()));
        if(routeFromDb.isEmpty()){
            throw new NotFoundException("Нет такого маршрута!");
        }
    }

}
