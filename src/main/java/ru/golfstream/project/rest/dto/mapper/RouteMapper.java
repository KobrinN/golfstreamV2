package ru.golfstream.project.rest.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.golfstream.project.entity.Route;
import ru.golfstream.project.rest.dto.request.RouteRequest;
import ru.golfstream.project.rest.dto.response.RouteResponse;

@Mapper
public interface RouteMapper {
    @Mapping(target = "instructor.id", source = "request.idInstructor")
    Route toModel(RouteRequest request);
    @Mapping(target = "idInstructor", source = "route.instructor.id")
    RouteRequest toRequest(Route route);
    RouteResponse toResponse(Route route);
    Route toModel(RouteResponse response);

}
