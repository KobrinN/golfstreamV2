package ru.golfstream.project.rest.dto.mapper;

import org.mapstruct.Mapper;
import ru.golfstream.project.entity.Route;
import ru.golfstream.project.entity.TypeEmployee;
import ru.golfstream.project.rest.dto.request.RouteRequest;
import ru.golfstream.project.rest.dto.request.TypeEmployeeRequest;
import ru.golfstream.project.rest.dto.response.RouteResponse;
import ru.golfstream.project.rest.dto.response.TypeEmployeeResponse;

@Mapper
public interface TypeEmployeeMapper {
    TypeEmployee toModel(TypeEmployeeRequest request);
    TypeEmployeeResponse toResponse(TypeEmployee route);
}
