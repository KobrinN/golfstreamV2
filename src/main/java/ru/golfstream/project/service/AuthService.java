package ru.golfstream.project.service;

import ru.golfstream.project.entity.User;
import ru.golfstream.project.rest.dto.request.UserLoginRequest;
import ru.golfstream.project.rest.dto.request.UserRequest;
import ru.golfstream.project.rest.dto.response.JwtResponse;
import ru.golfstream.project.security.UserDetailsImpl;

import java.util.Optional;

public interface AuthService {
    JwtResponse login(UserLoginRequest request);

    JwtResponse refresh(String token);

    User getByUsername(String username);
}
