package com.example.TechFellowQueryBuilder.controller;

import com.example.TechFellowQueryBuilder.api.UserAPI;
import com.example.TechFellowQueryBuilder.dto.request.UserRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.UserResponseDTO;
import com.example.TechFellowQueryBuilder.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

    private final UserService userService;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        return userService.getUser(userRequestDTO);
    }
}
