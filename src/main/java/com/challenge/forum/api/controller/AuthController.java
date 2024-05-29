package com.challenge.forum.api.controller;

import com.challenge.forum.api.dto.token.RequestTokenDto;
import com.challenge.forum.api.dto.token.ResponseTokenDto;
import com.challenge.forum.domain.User;
import com.challenge.forum.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

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
}
