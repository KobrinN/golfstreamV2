package ru.golfstream.project.service;

import ru.golfstream.project.rest.dto.request.RouteRequest;
import ru.golfstream.project.rest.dto.response.RouteResponse;

import java.util.List;

public interface RouteService extends Service<RouteRequest, RouteResponse> {
    List<RouteResponse> getRouteOfEmployee(Long id);

}
