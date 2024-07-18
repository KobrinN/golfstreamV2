package ru.golfstream.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.golfstream.project.entity.User;
import ru.golfstream.project.exception.exceptions.common.InvlaidFieldException;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.UserRepo;
import ru.golfstream.project.rest.dto.mapper.UserMapper;
import ru.golfstream.project.rest.dto.response.UserResponse;
import ru.golfstream.project.rest.dto.request.UserRequest;
import ru.golfstream.project.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;


    @Override
    public List<UserResponse> getAll() {
        List<User> all = userRepo.findAll();
        return all.stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public Long post(UserRequest request) {
        Pattern pattern = Pattern.compile("^[A-Z0-9+_.-]+@[A-Z0-9.-]+$");
        Matcher matcher = pattern.matcher(request.getMail());

        if (!StringUtils.hasText(request.getUsername()) ||
                StringUtils.hasText(request.getMail()) ||
                StringUtils.hasText(request.getPassword()) ||
                matcher.find()) {
            throw new InvlaidFieldException("Поля некорректные!");
        }

        User client = new User();
        client.setUsername(request.getUsername());
        client.setMail(request.getMail());
        client.setPassword(request.getPassword());

        userRepo.saveAndFlush(client);

        return client.getId();
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
        user = userMapper.toModel(request);
        userRepo.save(user);
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
