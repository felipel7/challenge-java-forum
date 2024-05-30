package com.challenge.forum.api.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseUpdateRequest(
    @NotNull
    Long id,

    @NotBlank
    String name,

    @NotBlank
    String category
) {
}
