package com.challenge.forum.api.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TopicCreateRequest(
    @NotBlank
    @Size(max = 255)
    String title,

    @NotBlank
    String content,

    @NotNull
    Long userId,

    @NotNull
    Long courseId
) {
}
