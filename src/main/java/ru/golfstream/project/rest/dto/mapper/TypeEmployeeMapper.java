package ru.golfstream.project.rest.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.golfstream.project.entity.Route;
import ru.golfstream.project.entity.TypeEmployee;
import ru.golfstream.project.rest.dto.request.RouteRequest;
import ru.golfstream.project.rest.dto.request.TypeEmployeeRequest;
import ru.golfstream.project.rest.dto.response.RouteResponse;
import ru.golfstream.project.rest.dto.response.TypeEmployeeResponse;

@Mapper
public interface TypeEmployeeMapper {
    TypeEmployee toModel(TypeEmployeeRequest request);
    TypeEmployeeRequest toRequest(TypeEmployee type);
    TypeEmployeeResponse toResponse(TypeEmployee route);
    TypeEmployee toModel(TypeEmployeeResponse response);
}
