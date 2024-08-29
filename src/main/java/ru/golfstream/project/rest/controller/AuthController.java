package ru.golfstream.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.golfstream.project.rest.dto.request.UserLoginRequest;
import ru.golfstream.project.rest.dto.request.UserRequest;
import ru.golfstream.project.rest.dto.response.JwtResponse;
import ru.golfstream.project.rest.dto.response.UserResponse;
import ru.golfstream.project.service.AuthService;
import ru.golfstream.project.service.UserService;

@RestController()
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginRequest request){
        JwtResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody UserRequest request){
        try{
            return ResponseEntity.ok(userService.post(request));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestBody String token){
        return ResponseEntity.ok(authService.refresh(token));
    }
}
