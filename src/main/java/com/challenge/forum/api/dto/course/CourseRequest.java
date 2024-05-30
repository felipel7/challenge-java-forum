package com.challenge.forum.api.dto.course;

import jakarta.validation.constraints.NotBlank;

public record CourseRequest(
    @NotBlank
    String name,

    @NotBlank
    String category
) {
}
