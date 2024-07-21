package ru.golfstream.project.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.golfstream.project.entity.User;
import ru.golfstream.project.exception.exceptions.common.NotFoundException;
import ru.golfstream.project.repos.UserRepo;
import ru.golfstream.project.rest.dto.mapper.UserMapper;
import ru.golfstream.project.rest.dto.mapper.UserMapperImpl;
import ru.golfstream.project.rest.dto.request.UserRequest;
import ru.golfstream.project.rest.dto.response.UserResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepo mockRepo;
    @InjectMocks
    private UserServiceImpl userService;
    private static final UserMapper userMapper = new UserMapperImpl();
    private static final List<User> users = new ArrayList<>();
    private static List<UserResponse> userResponses;
    private static List<UserRequest> userRequests;

    @BeforeAll
    static void init() {
        User user = new User();

        user.setId(1L);
        user.setUsername("one");
        user.setPassword("one");
        users.add(user);

        user.setId(2L);
        user.setUsername("two");
        user.setPassword("two");
        users.add(user);

        user.setId(3L);
        user.setUsername("three");
        user.setPassword("three");
        users.add(user);

        userResponses = users.stream().map(userMapper::toResponse).toList();
        userRequests = users.stream().map(userMapper::toRequest).toList();
    }

    @Test
    void getAll_should_return_list_of_three_user_response() {
        when(mockRepo.findAll()).thenReturn(users);

        List<UserResponse> usersReturned = userService.getAll();

        verify(mockRepo, times(1)).findAll();
        assertEquals(userResponses, usersReturned);
    }

    @Test
    void getAll_should_return_empty_list() {
        when(mockRepo.findAll()).thenReturn(List.of());

        List<UserResponse> usersReturned = userService.getAll();

        verify(mockRepo, times(1)).findAll();
        assertEquals(List.of(), usersReturned);
    }

    @Test
    void post_should_return_id_of_user() {
        when(mockRepo.saveAndFlush(any(User.class))).thenAnswer(invocationOnMock -> {
            User user = invocationOnMock.getArgument(0);
            user.setId(1L);
            return user;
        });

        Long returnedId = userService.post(userRequests.get(1));

        verify(mockRepo, times(1)).saveAndFlush(any(User.class));
        assertEquals(1L, returnedId);
    }

    @Test
    void getById_should_return_user() {
        when(mockRepo.findById(any(Long.class))).thenReturn(Optional.of(users.get(0)));;

        UserResponse returnedResponse = userService.getById(1L);

        verify(mockRepo, times(1)).findById(any(Long.class));
        assertEquals(userResponses.get(0), returnedResponse);
    }

    @Test
    void getById_should_throw_exception() {
        when(mockRepo.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getById(1L));
        verify(mockRepo, times(1)).findById(any(Long.class));
    }

    @Test
    void delete_should_call_delete_from_mock_one_times() {
        when(mockRepo.findById(any(Long.class))).thenReturn(Optional.of(new User()));
        userService.delete(1L);

        verify(mockRepo, times(1)).delete(any(User.class));
    }

    @Test
    void delete_should_never_call_delete_from_mock() {
        when(mockRepo.findById(any(Long.class))).thenReturn(Optional.empty());
        userService.delete(1L);

        verify(mockRepo, times(0)).delete(any(User.class));
    }

    @Test
    void edit_should_return_user_response() {
        when(mockRepo.findById(any(Long.class))).thenReturn(Optional.of(users.get(0)));
        when(mockRepo.saveAndFlush(any(User.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        UserResponse returnedResponse = userService.edit(1L, userRequests.get(0));

        verify(mockRepo, times(1)).findById(any(Long.class));
        verify(mockRepo, times(1)).saveAndFlush(any(User.class));
        assertEquals(userResponses.get(0), returnedResponse);
    }

    @Test
    void edit_should_throw_exception() {
        when(mockRepo.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.edit(1L, userRequests.get(0)));

        verify(mockRepo, times(1)).findById(any(Long.class));
        verify(mockRepo, times(0)).saveAndFlush(any(User.class));
    }
}