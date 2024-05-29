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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static com.challenge.forum.api.utils.Constants.*;

@RestController
@RequestMapping(AUTHENTICATION_CONTROLLER_ROUTE_MAP)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping(POST_USER_LOGIN_ROUTE)
    public ResponseEntity login(@RequestBody @Valid RequestTokenDto requestTokenDto) {
        var authToken = new UsernamePasswordAuthenticationToken(requestTokenDto.username(), requestTokenDto.password());
        var auth = authenticationManager.authenticate(authToken);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new ResponseTokenDto(token));
    }

    @PostMapping(POST_USER_REGISTER_ROUTE)
    public ResponseEntity<UserResponseDto> register(
        @RequestBody @Valid UserRequestDto userRequestDto,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        var user = userService.registerUser(userRequestDto);
        var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
}
