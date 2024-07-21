package ru.golfstream.project.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.golfstream.project.entity.Employee;
import ru.golfstream.project.entity.Route;
import ru.golfstream.project.repos.EmployeeRepo;
import ru.golfstream.project.repos.RouteRepo;
import ru.golfstream.project.rest.dto.mapper.RouteMapper;
import ru.golfstream.project.rest.dto.mapper.RouteMapperImpl;
import ru.golfstream.project.rest.dto.request.RouteRequest;
import ru.golfstream.project.rest.dto.response.RouteResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceImplTest {
    @Mock
    private RouteRepo routeRepo;
    @Mock
    private EmployeeRepo employeeRepo;
    @InjectMocks
    private RouteServiceImpl routeService;
    private static final RouteMapper routeMapper = new RouteMapperImpl();
    private static final List<Route> routes = new ArrayList<>();
    private static List<RouteRequest> requests;
    private static List<RouteResponse> responses;

    @BeforeAll
    static void init() {
        Route route = new Route();
        Employee employee = new Employee();

        route.setId(1L);
        employee.setId(1L);
        route.setInstructor(employee);
        routes.add(route);

        route.setId(2L);
        routes.add(route);

        route.setId(2L);
        routes.add(route);

        responses = routes.stream().map(routeMapper::toResponse).toList();
        requests = routes.stream().map(routeMapper::toRequest).toList();
    }

    @Test
    void getAll_should_return_list_of_three_routes() {
        when(routeRepo.findAll()).thenReturn(routes);

        List<RouteResponse> returnedResponses = routeService.getAll();

        verify(routeRepo, times(1)).findAll();
        assertEquals(responses, returnedResponses);
    }

    @Test
    void getAll_should_return_empty_list() {
        when(routeRepo.findAll()).thenReturn(List.of());

        List<RouteResponse> returnedResponses = routeService.getAll();

        verify(routeRepo, times(1)).findAll();
        assertEquals(List.of(), returnedResponses);
    }

    @Test
    void getById() {
        when(routeRepo.findById(any(Long.class))).thenReturn(Optional.of(routes.get(0)));


    }

    @Test
    void getRouteOfEmployee() {
    }

    @Test
    void post() {
    }

    @Test
    void edit() {
    }

    @Test
    void delete() {
    }

    @Test
    void checkExistAndGetEmployeeById() {
    }
}