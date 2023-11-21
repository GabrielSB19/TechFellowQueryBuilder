package com.example.TechFellowQueryBuilder.controller;

import com.example.TechFellowQueryBuilder.api.UserAPI;
import com.example.TechFellowQueryBuilder.dto.request.UserRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.UserResponseDTO;
import com.example.TechFellowQueryBuilder.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class that handles HTTP requests related to users.
 */
@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

    private final UserService userService;

    /**
     * Creates a new user.
     *
     * @param userRequestDTO The request DTO containing information for the user creation.
     * @return The response DTO containing information about the created user.
     */
    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        return userService.getUser(userRequestDTO);
    }
}
