package com.challenge.forum.services;

import com.challenge.forum.api.dto.course.CourseRequest;
import com.challenge.forum.api.dto.course.CourseResponse;
import com.challenge.forum.api.dto.course.CourseUpdateRequest;
import com.challenge.forum.api.utils.CopyUtils;
import com.challenge.forum.domain.Course;
import com.challenge.forum.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Page<CourseResponse> getCoursesPage(Pageable pageable) {
        var courses = courseRepository.findAll(pageable);
        return courses.map(CourseResponse::new);
    }

    public CourseResponse saveCourse(CourseRequest courseRequest) {
        var course = courseRepository.save(new Course(courseRequest));
        return new CourseResponse(course);
    }

    public CourseResponse updateCourse(CourseUpdateRequest courseUpdateRequest) {
        var course = courseRepository.getReferenceById(courseUpdateRequest.id());
        CopyUtils.copyNonNullProperties(courseUpdateRequest, course);
        return new CourseResponse(course);
    }
}
