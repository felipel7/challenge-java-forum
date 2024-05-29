package com.challenge.forum.services;

import com.challenge.forum.api.dto.user.UserRequestDto;
import com.challenge.forum.api.dto.user.UserResponseDto;
import com.challenge.forum.domain.User;
import com.challenge.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        var encodedPassword = encryptPassword(userRequestDto.getPassword());
        userRequestDto.setPassword(encodedPassword);
        var user = userRepository.save(new User(userRequestDto));
        return new UserResponseDto(user);
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
