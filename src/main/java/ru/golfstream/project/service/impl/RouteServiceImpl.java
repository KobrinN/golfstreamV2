package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.Employee;
import ru.golfstream.project.entity.Route;
import ru.golfstream.project.entity.Voucher;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.exception.exceptions.route.TimeMismatchException;
import ru.golfstream.project.repos.RouteRepo;
import ru.golfstream.project.rest.dto.NewOrUpdateRouteRequest;
import ru.golfstream.project.rest.dto.RouteDto;
import ru.golfstream.project.rest.dto.VoucherDto;
import ru.golfstream.project.service.EmployeeService;
import ru.golfstream.project.service.RouteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepo routeRepo;
    private final VoucherServiceImpl voucherService;
    private final EmployeeService employeeService;
    @Override
    public List<RouteDto> getAll() {
        List<Route> all = routeRepo.findAll();
        return all.stream()
                .map(route -> RouteDto.builder()
                        .arrival(route.getArrival())
                        .departure(route.getDeparture())
                        .fromWhere(route.getFromWhere())
                        .toWhere(route.getToWhere())
                        .transportation(route.getTransportation())
                        .build())
                .toList();
    }

    @Override
    public RouteDto getById(Integer id) {
        Optional<Route> routeFromDb = routeRepo.findById(id);

        if(routeFromDb.isEmpty()){
            throw new NotFoundException("Нет маршрута с id = " + id + "!");
        }

        Route route = routeFromDb.get();
        return buildRouteDto(route);
    }

    @Override
    public List<VoucherDto> getVouchersByRouteId(Integer id) {
        Optional<Route> routeFromDb = routeRepo.findById(id);

        if(routeFromDb.isEmpty()){
            throw new NotFoundException("Нет маршрута с id = " + id + "!");
        }

        List<Voucher> voucherFromDb = voucherService.getByRouteId(id);
        return voucherFromDb.stream()
                .map(VoucherServiceImpl::buildVoucherDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer add(NewOrUpdateRouteRequest request) {
        Employee employee = employeeService.findEmployeeById(request.getIdInstructor());
        if(request.getArrival().isAfter(request.getDeparture())){
            throw new TimeMismatchException("Время отправление позже времени прибытия!");
        }
        Route route = buildRoute(employee, request);
        routeRepo.saveAndFlush(route);
        return route.getId();
    }

    @Override
    public RouteDto updateById(Integer id, NewOrUpdateRouteRequest request) {
        Optional<Route> route = routeRepo.findById(id);
        Employee employee = employeeService.findEmployeeById(request.getIdInstructor());
        if(route.isEmpty()){
            throw new NotFoundException("Нет такого маршрута!");
        }

        Route routeFromDb = route.get();
        routeFromDb = buildRoute(employee, request);
        routeRepo.saveAndFlush(routeFromDb);

        return buildRouteDto(routeFromDb);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Route> routeFromDb = routeRepo.findById(id);
        if(routeFromDb.isEmpty()){
            throw new NotFoundException("Нет такого маршрута!");
        }

        routeRepo.deleteById(id);
    }

    private static RouteDto buildRouteDto(Route route){
        return RouteDto.builder()
                .arrival(route.getArrival())
                .departure(route.getDeparture())
                .fromWhere(route.getFromWhere())
                .toWhere(route.getToWhere())
                .transportation(route.getTransportation())
                .build();
    }

    private static Route buildRoute(Employee employee, NewOrUpdateRouteRequest request){
        Route route = new Route();
        route.setArrival(request.getArrival());
        route.setDeparture(request.getDeparture());
        route.setFromWhere(request.getFromWhere());
        route.setToWhere(request.getToWhere());
        route.setTransportation(request.getTransportation());
        route.setIdInstructor(employee);

        return route;
    }
}
