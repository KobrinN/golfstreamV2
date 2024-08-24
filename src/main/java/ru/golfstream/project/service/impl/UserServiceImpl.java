package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.golfstream.project.entity.Role;
import ru.golfstream.project.entity.User;
import ru.golfstream.project.exception.exceptions.common.InvlaidFieldException;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.UserRepo;
import ru.golfstream.project.rest.dto.mapper.UserMapper;
import ru.golfstream.project.rest.dto.mapper.UserMapperImpl;
import ru.golfstream.project.rest.dto.response.UserResponse;
import ru.golfstream.project.rest.dto.request.UserRequest;
import ru.golfstream.project.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper = new UserMapperImpl();
    private final PasswordEncoder encoder;


    @Override
    public List<UserResponse> getAll() {
        List<User> all = userRepo.findAll();
        return all.stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public Long post(UserRequest request) {
        if (userRepo.findByUsername(request.getUsername()).isPresent()) throw new IllegalArgumentException("!User with this username already exist!");

        User user = userMapper.toModel(request);
        user.setRegistrationDate(LocalDate.now());
        user.setRoles(Set.of(Role.ROLE_ADMIN));
        user.setPassword(encoder.encode(request.getPassword()));
        user = userRepo.save(user);

        return user.getId();
    }

    @Override
    public UserResponse getById(Long id) {
        User user = checkExistUserAndGet(id);
        return userMapper.toResponse(user);
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepo.findById(id);
        user.ifPresent(userRepo::delete);
    }

    @Override
    public UserResponse edit(Long id, UserRequest request) {
        User user = checkExistUserAndGet(id);

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setMail(request.getMail());

        user = userRepo.saveAndFlush(user);
        return userMapper.toResponse(user);
    }

    private User checkExistUserAndGet(Long id) throws NotFoundException{
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("Not found USER with id: " + id + "!");
        }
        return user.get();
    }
}
