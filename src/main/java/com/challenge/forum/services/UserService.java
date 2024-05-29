package com.challenge.forum.services;

import com.challenge.forum.api.dto.user.UserRequestDto;
import com.challenge.forum.api.dto.user.UserResponseDto;
import com.challenge.forum.domain.User;
import com.challenge.forum.exceptions.businessExceptions.UserAlreadyExistsException;
import com.challenge.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.challenge.forum.api.utils.Constants.USERNAME_REGISTERED;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        var userExists = userRepository.existsByUsernameOrEmail(
            userRequestDto.getUsername(), userRequestDto.getEmail());
        if (userExists) throw new UserAlreadyExistsException(USERNAME_REGISTERED);

        var encodedPassword = encryptPassword(userRequestDto.getPassword());
        userRequestDto.setPassword(encodedPassword);
        var user = userRepository.save(new User(userRequestDto));
        return new UserResponseDto(user);
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
