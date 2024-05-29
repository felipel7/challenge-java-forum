package com.challenge.forum.api.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
