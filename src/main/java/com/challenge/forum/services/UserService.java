package com.challenge.forum.services;

import com.challenge.forum.api.dto.user.UserCreateRequest;
import com.challenge.forum.api.dto.user.UserResponse;
import com.challenge.forum.domain.User;
import com.challenge.forum.exceptions.businessExceptions.UserAlreadyExistsException;
import com.challenge.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.challenge.forum.api.utils.Constants.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse registerUser(UserCreateRequest userCreateRequest) {
        var userExists = userRepository.existsByUsernameOrEmail(
            userCreateRequest.getUsername(), userCreateRequest.getEmail());
        if (userExists) throw new UserAlreadyExistsException(USER_ALREADY_REGISTERED);

        var encodedPassword = encryptPassword(userCreateRequest.getPassword());
        userCreateRequest.setPassword(encodedPassword);
        var user = userRepository.save(new User(userCreateRequest));
        return new UserResponse(user);
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
