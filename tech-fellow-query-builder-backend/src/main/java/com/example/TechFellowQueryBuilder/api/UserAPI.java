package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.request.UserRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.UserResponseDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

/**
 * This interface represents the User API for handling user-related operations.
 */
@RequestMapping(UserAPI.BASE_URL)
public interface UserAPI {

    /**
     * The base URL for user-related API endpoints.
     */
    String BASE_URL = "/api/user";

    /**
     * Endpoint for creating a new user.
     *
     * @param userRequestDTO The data transfer object (DTO) containing user creation request information.
     * @return A UserResponseDTO containing the response data for the created user.
     */
    @PostMapping("/create")
    @CrossOrigin("*")
    UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO userRequestDTO);
}
