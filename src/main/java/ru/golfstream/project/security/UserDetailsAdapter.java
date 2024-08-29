package ru.golfstream.project.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.golfstream.project.entity.User;

import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsAdapter {
    public static UserDetailsImpl createUserDetails(User user){
        UserDetailsImpl userDetails =  new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(Enum::name)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );

        return userDetails;
    }
}
