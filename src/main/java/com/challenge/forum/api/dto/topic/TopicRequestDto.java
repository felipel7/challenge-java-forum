package com.challenge.forum.api.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TopicRequestDto(
    @NotBlank
    @Size(max = 255)
    String title,

    @NotBlank String content,

    Long userId,
    Long courseId
) {
}
