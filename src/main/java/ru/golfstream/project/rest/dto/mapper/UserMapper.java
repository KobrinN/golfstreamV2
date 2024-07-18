package ru.golfstream.project.rest.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.golfstream.project.entity.User;
import ru.golfstream.project.rest.dto.request.UserRequest;
import ru.golfstream.project.rest.dto.response.UserResponse;

@Mapper
@Component
public interface UserMapper {
    User toModel(UserRequest request);
    UserResponse toResponse(User user);
}
