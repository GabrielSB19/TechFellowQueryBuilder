package com.example.TechFellowQueryBuilder.Unit.service;

import com.example.TechFellowQueryBuilder.dto.request.UserRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.UserResponseDTO;
import com.example.TechFellowQueryBuilder.mapper.UserMapper;
import com.example.TechFellowQueryBuilder.mapper.UserMapperImpl;
import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import com.example.TechFellowQueryBuilder.repository.UserRepository;
import com.example.TechFellowQueryBuilder.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class UserServiceTest {

    private UserMapper userMapper;
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void setup() {
        userMapper = spy(UserMapperImpl.class);
        userRepository = mock(UserRepository.class);

        userService = new UserService(userRepository, userMapper);
    }

    @Test
    void createUserTest() {
        UserClient userClient = new UserClient();
        userClient.setUsername("test");
        when(userRepository.save(any(UserClient.class))).thenReturn(userClient);
        when(userMapper.toResponseDTO(any(UserClient.class))).thenReturn(new UserResponseDTO());

        UserResponseDTO userResponseDTO = userService.createUser(userClient);
        assertEquals(Collections.emptyList(), userClient.getQueries());
        assertEquals(Collections.emptyList(), userClient.getComments());

        verify(userMapper, times(1)).toResponseDTO(any(UserClient.class));
        verify(userRepository, times(1)).save(any(UserClient.class));

        assertEquals(UserResponseDTO.class, userResponseDTO.getClass());
    }

    @Test
    void createUserWithOutNameTest() {
        UserClient userClient = new UserClient();
        userClient.setUsername(null);
        when(userRepository.save(any(UserClient.class))).thenReturn(userClient);
        when(userMapper.toResponseDTO(any(UserClient.class))).thenReturn(new UserResponseDTO());

        UserResponseDTO userResponseDTO = userService.createUser(userClient);
        assertEquals(Collections.emptyList(), userClient.getQueries());
        assertEquals(Collections.emptyList(), userClient.getComments());

        verify(userMapper, times(1)).toResponseDTO(any(UserClient.class));
        verify(userRepository, times(1)).save(any(UserClient.class));

        assertEquals(UserResponseDTO.class, userResponseDTO.getClass());
    }

    @Test
    void getUserTest() {
        UserClient userClient = new UserClient();
        when(userRepository.save(any(UserClient.class))).thenReturn(userClient);
        when(userMapper.toResponseDTO(any(UserClient.class))).thenReturn(new UserResponseDTO());

        UserResponseDTO userResponseDTO = userService.createUser(userClient);
        assertEquals(Collections.emptyList(), userClient.getQueries());
        assertEquals(Collections.emptyList(), userClient.getComments());

        verify(userMapper, times(1)).toResponseDTO(any(UserClient.class));
        verify(userRepository, times(1)).save(any(UserClient.class));

        assertEquals(UserResponseDTO.class, userResponseDTO.getClass());
    }

    @Test
    void testGetUserWhenUserNotExists() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("nonExistingUser");

        when(userRepository.findByUsername("nonExistingUser")).thenReturn(Optional.empty());

        UserClient mockedUserClient = new UserClient();
        UserResponseDTO mockedResponseDTO = new UserResponseDTO();
        when(userMapper.toUser(userRequestDTO)).thenReturn(mockedUserClient);
        when(userRepository.save(mockedUserClient)).thenReturn(mockedUserClient);
        when(userMapper.toResponseDTO(any(UserClient.class))).thenReturn(mockedResponseDTO);

        UserResponseDTO result = userService.getUser(userRequestDTO);

        verify(userMapper, times(1)).toUser(userRequestDTO);
        verify(userMapper, times(1)).toResponseDTO(mockedUserClient);
        assertEquals(mockedResponseDTO, result);
    }

}
