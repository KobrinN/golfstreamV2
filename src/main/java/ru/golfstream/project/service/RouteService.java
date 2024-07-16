package ru.golfstream.project.service;

import ru.golfstream.project.rest.dto.request.RouteRequest;
import ru.golfstream.project.rest.dto.response.RouteDto;

import java.util.List;

public interface RouteService {
    List<RouteDto> getAll();

    RouteDto getById(Long id);

    List<RouteDto> findRouteOfEmployee(Long id);

    Long add(RouteRequest routeRequest);

    RouteDto updateById(Long id, RouteRequest request);

    void deleteById(Long id);
}
