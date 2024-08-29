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
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.EmployeeRepo;
import ru.golfstream.project.repos.RouteRepo;
import ru.golfstream.project.rest.dto.mapper.RouteMapper;
import ru.golfstream.project.rest.dto.mapper.RouteMapperImpl;
import ru.golfstream.project.rest.dto.request.RouteRequest;
import ru.golfstream.project.rest.dto.response.RouteResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private static Employee employee;
    @BeforeAll
    static void init() {
        Route route = new Route();
        employee = new Employee();

        route.setId(1L);
        employee.setId(1L);
        route.setInstructor(employee);
        routes.add(route);

        route.setId(2L);
        routes.add(route);

        route.setId(2L);
        routes.add(route);

        employee.setRoutes(routes);
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
    void getById_should_return_user() {
        when(routeRepo.findById(any(Long.class))).thenReturn(Optional.of(routes.get(0)));

        RouteResponse response = routeService.getById(1L);

        verify(routeRepo, times(1)).findById(any(Long.class));
        assertEquals(responses.get(0), response);

    }

    @Test
    void getById_should_throw_exception() {
        when(routeRepo.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> routeService.getById(1L));
        verify(routeRepo, times(1)).findById(any(Long.class));
    }

    @Test
    void getRouteOfEmployee_should_return_list_of_routes() {
        when(employeeRepo.findByIdWithRoute(any(Long.class))).thenReturn(Optional.of(employee));

        List<RouteResponse> routeResponses = routeService.getRouteOfEmployee(1L);

        verify(employeeRepo, times(1)).findByIdWithRoute(any(Long.class));
        assertEquals(responses, routeResponses);
    }

    @Test
    void getRouteOfEmployee_should_throw_exception() {
        when(employeeRepo.findByIdWithRoute(any(Long.class))).thenReturn(Optional.empty());

       assertThrows(NotFoundException.class, () -> routeService.getRouteOfEmployee(1L));
        verify(employeeRepo, times(1)).findByIdWithRoute(any(Long.class));
    }

    @Test
    void post_should_return_id() {
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