package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.Employee;
import ru.golfstream.project.entity.Route;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.exception.exceptions.common.TimeMismatchException;
import ru.golfstream.project.repos.EmployeeRepo;
import ru.golfstream.project.repos.RouteRepo;
import ru.golfstream.project.rest.dto.request.RouteRequest;
import ru.golfstream.project.rest.dto.RouteDto;
import ru.golfstream.project.service.RouteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepo routeRepo;
    private final EmployeeRepo employeeRepo;
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
    public RouteDto getById(Long id) {
        Optional<Route> routeFromDb = routeRepo.findById(id);

        if(routeFromDb.isEmpty()){
            throw new NotFoundException("Нет маршрута с id = " + id + "!");
        }

        Route route = routeFromDb.get();
        return buildRouteDto(route);
    }

    @Override
    public List<RouteDto> findRouteOfEmployee(Long id) {
        Optional<Employee> employeeFromDb = employeeRepo.findById(id);
        if(employeeFromDb.isEmpty()){
            throw new NotFoundException("Нет работника с ID = " + id + "!");
        }
        Employee employee = employeeFromDb.get();
        return employee.getRoutes().stream()
                .map(this::buildRouteDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long add(RouteRequest request) {
        Optional<Employee> employeeFromDb = employeeRepo.findById(request.getIdInstructor());
        if(employeeFromDb.isEmpty()){
            throw new NotFoundException("Нет работника с ID = " + request.getIdInstructor() + "!");
        }
        if(request.getArrival().isAfter(request.getDeparture())){
            throw new TimeMismatchException("Время отправление позже времени прибытия!");
        }
        Route route = buildRoute(employeeFromDb.get(), request);
        routeRepo.saveAndFlush(route);
        return route.getId();
    }

    @Override
    public RouteDto updateById(Long id, RouteRequest request) {
        Optional<Route> route = routeRepo.findById(id);
        Optional<Employee> employeeFromDb = employeeRepo.findById(request.getIdInstructor());
        if(employeeFromDb.isEmpty()){
            throw new NotFoundException("Нет работника с ID = " + 1 + "!");
        }

        Route routeFromDb = route.get();
        routeFromDb = buildRoute(employeeFromDb.get(), request);
        routeRepo.saveAndFlush(routeFromDb);

        return buildRouteDto(routeFromDb);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Route> routeFromDb = routeRepo.findById(id);
        if(routeFromDb.isEmpty()){
            throw new NotFoundException("Нет такого маршрута!");
        }

        routeRepo.deleteById(id);
    }

    protected RouteDto buildRouteDto(Route route){
        return RouteDto.builder()
                .arrival(route.getArrival())
                .departure(route.getDeparture())
                .fromWhere(route.getFromWhere())
                .toWhere(route.getToWhere())
                .transportation(route.getTransportation())
                .build();
    }

    private static Route buildRoute(Employee employee, RouteRequest request){
        Route route = new Route();
        route.setArrival(request.getArrival());
        route.setDeparture(request.getDeparture());
        route.setFromWhere(request.getFromWhere());
        route.setToWhere(request.getToWhere());
        route.setTransportation(request.getTransportation());
        route.setInstructor(employee);

        return route;
    }
}
