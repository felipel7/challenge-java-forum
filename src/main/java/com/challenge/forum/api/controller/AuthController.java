package com.challenge.forum.api.controller;

import com.challenge.forum.api.dto.token.RequestTokenDto;
import com.challenge.forum.api.dto.token.ResponseTokenDto;
import com.challenge.forum.api.dto.user.UserRequestDto;
import com.challenge.forum.api.dto.user.UserResponseDto;
import com.challenge.forum.domain.User;
import com.challenge.forum.services.TokenService;
import com.challenge.forum.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid RequestTokenDto requestTokenDto) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(
                requestTokenDto.username(), requestTokenDto.password());
            var auth = authenticationManager.authenticate(authToken);
            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new ResponseTokenDto(token));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
        @RequestBody @Valid UserRequestDto userRequestDto,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        var user = userService.createUser(userRequestDto);
        var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
}
