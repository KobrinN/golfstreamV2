package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.Route;
import ru.golfstream.project.entity.User;
import ru.golfstream.project.entity.Voucher;
import ru.golfstream.project.exception.exceptions.client.NotFoundVoucherOfThisClient;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.PurchaseRepo;
import ru.golfstream.project.repos.RouteRepo;
import ru.golfstream.project.repos.UserRepo;
import ru.golfstream.project.repos.VoucherRepo;
import ru.golfstream.project.rest.dto.mapper.VoucherMapper;
import ru.golfstream.project.rest.dto.request.VoucherRequest;
import ru.golfstream.project.rest.dto.response.VoucherResponse;
import ru.golfstream.project.service.VoucherService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {
    private final UserRepo userRepo;
    private final VoucherRepo voucherRepo;
    private final RouteRepo routeRepo;
    private final VoucherMapper voucherMapper;

    @Override
    public List<Voucher> getByRouteId(Long id) {
        return voucherRepo.findByRoute(routeRepo.findById(id).get());
    }

    @Override
    public List<VoucherResponse> getAll() {
        List<Voucher> vouchers = voucherRepo.findAll();
        return vouchers.stream()
                .map(voucherMapper::toResponse)
                .toList();
    }

    @Override
    public List<VoucherResponse> getVouchersByRouteId(Long id) {
        Route route = checkExistAndGetRouteById(id);
        List<Voucher> vouchers = voucherRepo.findByRoute(route);
        return vouchers.stream()
                .map(voucherMapper::toResponse)
                .toList();
    }

    @Override
    public VoucherResponse getById(Long id) {
        Voucher voucher = checkExistVoucherAndGet(id);
        return voucherMapper.toResponse(voucher);
    }

    @Override
    public Long post(VoucherRequest request) {
        Voucher voucher = voucherMapper.toModel(request);
        voucherRepo.saveAndFlush(voucher);
        return voucher.getId();
    }

    @Override
    public VoucherResponse edit(Long id, VoucherRequest request) {
        Voucher voucher = checkExistVoucherAndGet(id);
        voucher = voucherMapper.toModel(request);
        voucherRepo.save(voucher);
        return voucherMapper.toResponse(voucher);
    }

    @Override
    public void delete(Long id) {
        Optional<Voucher> voucher = voucherRepo.findById(id);
        voucher.ifPresent(voucherRepo::delete);
    }

    @Override
    public List<VoucherResponse> findVouchersByUserId(Long id) {
        User user = checkExistUserAndGet(id);
        List<Voucher> clientAndVoucherDtoList = userRepo.getVoucherOfClientDto(id);

        if (clientAndVoucherDtoList.isEmpty()) {
            throw new NotFoundVoucherOfThisClient("Не найдены путёвки этого пользователя!");
        }

        return clientAndVoucherDtoList.stream()
                .map(voucherMapper::toResponse)
                .toList();
    }

    private Voucher checkExistVoucherAndGet(Long id) throws NotFoundException {
        Optional<Voucher> voucher = voucherRepo.findById(id);
        if (voucher.isEmpty()) {
            throw new NotFoundException("Not found VOUCHER with id: " + id + "!");
        }
        return voucher.get();
    }

    private Route checkExistAndGetRouteById(Long id){
        Optional<Route> route = routeRepo.findById(id);
        if(route.isEmpty()) throw new NotFoundException("Not found ROUTE with id: " + id + "!");

        return route.get();
    }

    private User checkExistUserAndGet(Long id) throws NotFoundException{
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("Not found USER with id: " + id + "!");
        }
        return user.get();
    }
}
