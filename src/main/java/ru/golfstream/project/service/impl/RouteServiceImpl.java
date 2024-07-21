package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.Employee;
import ru.golfstream.project.entity.Route;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.exception.exceptions.common.TimeMismatchException;
import ru.golfstream.project.repos.EmployeeRepo;
import ru.golfstream.project.repos.RouteRepo;
import ru.golfstream.project.rest.dto.mapper.RouteMapper;
import ru.golfstream.project.rest.dto.mapper.RouteMapperImpl;
import ru.golfstream.project.rest.dto.request.RouteRequest;
import ru.golfstream.project.rest.dto.response.RouteResponse;
import ru.golfstream.project.service.RouteService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepo routeRepo;
    private final EmployeeRepo employeeRepo;
    private final RouteMapper routeMapper = new RouteMapperImpl();

    @Override
    public List<RouteResponse> getAll() {
        List<Route> all = routeRepo.findAll();
        return all.stream()
                .map(routeMapper::toResponse)
                .toList();
    }

    @Override
    public RouteResponse getById(Long id) {
        Route route = checkExistAndGetRouteById(id);
        return routeMapper.toResponse(route);
    }

    @Override
    public List<RouteResponse> getRouteOfEmployee(Long id) {
        Employee employee = checkExistAndGetEmployeeById(id);
        return employee.getRoutes().stream()
                .map(routeMapper::toResponse)
                .toList();
    }

    @Override
    public Long post(RouteRequest request) {
        checkExistEmployee(request.getIdInstructor());
        if (request.getArrival().isAfter(request.getDeparture())) {
            throw new TimeMismatchException("ARRIVAL is after DEPARTURE!");
        }
        Route route = routeMapper.toModel(request);
        routeRepo.saveAndFlush(route);
        return route.getId();
    }

    @Override
    public RouteResponse edit(Long id, RouteRequest request) {
        Employee employee = checkExistAndGetEmployeeById(request.getIdInstructor());
        Route route = checkExistAndGetRouteById(id);

        route.setArrival(request.getArrival());
        route.setDeparture(request.getDeparture());
        route.setInstructor(employee);
        route.setFromWhere(request.getFromWhere());
        route.setToWhere(request.getToWhere());
        route.setTransportation(request.getTransportation());

        routeRepo.saveAndFlush(route);
        return routeMapper.toResponse(route);
    }

    @Override
    public void delete(Long id) {
        Optional<Route> route = routeRepo.findById(id);
        route.ifPresent(routeRepo::delete);
    }

    private Route checkExistAndGetRouteById(Long id) {
        Optional<Route> route = routeRepo.findById(id);
        if (route.isEmpty()) throw new NotFoundException("Not found ROUTE with id: " + id + "!");

        return route.get();
    }

    private void checkExistEmployee(Long id) {
        if (!employeeRepo.existsById(id)) {
            throw new NotFoundException("Not found EMPLOYEE with id: " + id + "!");
        }
    }

    protected Employee checkExistAndGetEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepo.findByIdWithRoute(id);
        if (employee.isEmpty()) throw new NotFoundException("Not found EMPLOYEE with id: " + id + "!");
        return employee.get();
    }
}
