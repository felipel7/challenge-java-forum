package com.challenge.forum.api.controller;

import com.challenge.forum.api.dto.token.TokenRequest;
import com.challenge.forum.api.dto.token.TokenResponse;
import com.challenge.forum.api.dto.user.UserCreateRequest;
import com.challenge.forum.api.dto.user.UserResponse;
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
    public ResponseEntity login(@RequestBody @Valid TokenRequest tokenRequest) {
        var authToken = new UsernamePasswordAuthenticationToken(tokenRequest.username(), tokenRequest.password());
        var auth = authenticationManager.authenticate(authToken);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping(POST_USER_REGISTER_ROUTE)
    public ResponseEntity<UserResponse> register(
        @RequestBody @Valid UserCreateRequest userCreateRequest,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        var user = userService.registerUser(userCreateRequest);
        var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
}
