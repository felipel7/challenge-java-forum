package com.challenge.forum.api.dto.topic;

import com.challenge.forum.domain.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TopicRequestUpdateDto(
    @NotNull
    Long id,

    @Size(max = 255)
    String title,

    String content,
    Status status,
    Long courseId
) {
}
