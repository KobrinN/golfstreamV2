package ru.golfstream.project.service;

import ru.golfstream.project.rest.dto.response.UserResponse;
import ru.golfstream.project.rest.dto.request.UserRequest;

import java.util.List;

public interface UserService extends Service<UserRequest, UserResponse> {
}
