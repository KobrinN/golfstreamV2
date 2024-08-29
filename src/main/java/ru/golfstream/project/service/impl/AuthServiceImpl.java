package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.golfstream.project.entity.User;
import ru.golfstream.project.repos.UserRepo;
import ru.golfstream.project.rest.dto.request.UserLoginRequest;
import ru.golfstream.project.rest.dto.request.UserRequest;
import ru.golfstream.project.rest.dto.response.JwtResponse;
import ru.golfstream.project.security.JwtTokenProvider;
import ru.golfstream.project.security.UserDetailsImpl;
import ru.golfstream.project.service.AuthService;
import ru.golfstream.project.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final JwtTokenProvider provider;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager manager;

    @Override
    public JwtResponse login(UserLoginRequest request) {
        var userDetails = getByUsername(request.getUsername());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setUsername(userDetails.getUsername());
        jwtResponse.setId(userDetails.getId());
        jwtResponse.setRefreshToken(provider.createRefreshToken(userDetails));
        jwtResponse.setAccessToken(provider.createAccessToken(userDetails));

        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String token) {
        return provider.refreshUserTokens(token);
    }

    public User getByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found USER with username: " + username + "!"));
    }
}
