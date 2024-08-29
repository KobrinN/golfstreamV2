package ru.golfstream.project.service;

import ru.golfstream.project.entity.User;
import ru.golfstream.project.rest.dto.response.UserResponse;
import ru.golfstream.project.rest.dto.request.UserRequest;
import ru.golfstream.project.security.UserDetailsImpl;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service<UserRequest, UserResponse> {
}
