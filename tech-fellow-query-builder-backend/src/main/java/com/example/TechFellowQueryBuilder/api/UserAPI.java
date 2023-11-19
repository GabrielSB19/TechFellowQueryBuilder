package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.request.UserRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.UserResponseDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

@RequestMapping(UserAPI.BASE_URL)
public interface UserAPI {

    String BASE_URL = "/api/user";

    @PostMapping("/create")
    @CrossOrigin("*")
    UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO userRequestDTO);
}
