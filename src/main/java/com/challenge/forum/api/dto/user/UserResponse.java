package com.challenge.forum.api.dto.user;

import com.challenge.forum.domain.User;

public record UserResponse(
    Long id,
    String name,
    String email,
    String username
) {
    public UserResponse(User user) {
        this(user.getId(), user.getUsername(), user.getEmail(), user.getName());
    }
}
