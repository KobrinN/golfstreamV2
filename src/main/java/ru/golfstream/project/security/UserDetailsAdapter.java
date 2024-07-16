package ru.golfstream.project.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.golfstream.project.entity.User;

import java.util.stream.Collectors;

public class UserDetailsAdapter {
    public static UserDetailsImpl createUserDetails(User user){
        return UserDetailsImpl.builder()
                .id(user.getId())
                .username(user.getUsername())
                .authorities(user.getRoles().stream()
                        .map(Enum::name)
                        .map(SimpleGrantedAuthority::new )
                        .collect(Collectors.toList()))
                .build();
    }
}
