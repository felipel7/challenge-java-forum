package com.challenge.forum.api.dto.course;

import com.challenge.forum.domain.Course;

public record CourseResponse(
    Long id,
    String name,
    String category
) {
    public CourseResponse(Course course) {
        this(course.getId(), course.getName(), course.getCategory());
    }
}
