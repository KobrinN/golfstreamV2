package ru.golfstream.project.service;

import ru.golfstream.project.rest.dto.NewOrUpdateRouteRequest;
import ru.golfstream.project.rest.dto.RouteDto;
import ru.golfstream.project.rest.dto.VoucherDto;

import java.util.List;

public interface RouteService {
    List<RouteDto> getAll();

    RouteDto getById(Integer id);

    List<VoucherDto> getVouchersByRouteId(Integer id);

    Integer add(NewOrUpdateRouteRequest newOrUpdateRouteRequest);

    RouteDto updateById(Integer id, NewOrUpdateRouteRequest request);

    void deleteById(Integer id);
}
