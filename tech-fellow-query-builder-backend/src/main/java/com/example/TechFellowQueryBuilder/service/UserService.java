package com.example.TechFellowQueryBuilder.service;

import com.example.TechFellowQueryBuilder.dto.request.UserRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.UserResponseDTO;
import com.example.TechFellowQueryBuilder.mapper.UserMapper;
import com.example.TechFellowQueryBuilder.model.ownModel.Query;
import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import com.example.TechFellowQueryBuilder.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Retrieves a user based on the provided UserRequestDTO.
     *
     * @param userRequestDTO The DTO containing user information.
     * @return A UserResponseDTO containing the user information.
     */
    public UserResponseDTO getUser(UserRequestDTO userRequestDTO) {
        UserClient userClient = userMapper.toUser(userRequestDTO);

    Optional<UserClient> userClientOptional = userRepository.findByUsername(userClient.getUsername());

        if (userClientOptional.isPresent()) {
            return userMapper.toResponseDTO(userClientOptional.get());
        } else {
            return createUser(userClient);
        }
    }

    /**
     * Creates a new user based on the provided UserClient.
     *
     * @param userClient The user information to be created.
     * @return A UserResponseDTO containing the created user information.
     */
    public UserResponseDTO createUser(UserClient userClient) {
        userClient.setQueries(Collections.emptyList());
        userClient.setComments(Collections.emptyList());
        return userMapper.toResponseDTO(userRepository.save(userClient));
    }
}
