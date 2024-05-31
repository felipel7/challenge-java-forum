package com.challenge.forum.api.controller;

import com.challenge.forum.api.dto.course.CourseRequest;
import com.challenge.forum.api.dto.course.CourseResponse;
import com.challenge.forum.api.dto.course.CourseUpdateRequest;
import com.challenge.forum.services.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static com.challenge.forum.api.utils.Constants.*;

@RestController
@RequestMapping(COURSE_CONTROLLER_ROUTE_MAP)
@Tag(name = DOCS_COURSE_TAG_NAME)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<Page<CourseResponse>> getCourses(
        @PageableDefault(size = 5) Pageable pageable
    ) {
        var page = courseService.getCoursesPage(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    @SecurityRequirement(name = DOCS_BEARER_KEY)
    public ResponseEntity<CourseResponse> saveCourse(
        @Valid @RequestBody CourseRequest courseRequest,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        var course = courseService.saveCourse(courseRequest);
        var uri = uriComponentsBuilder.path("/courses/{id}").buildAndExpand(course.id()).toUri();
        return ResponseEntity.created(uri).body(course);
    }

    @PutMapping
    @Transactional
    @SecurityRequirement(name = DOCS_BEARER_KEY)
    public ResponseEntity<CourseResponse> updateCourse(
        @Valid @RequestBody CourseUpdateRequest courseUpdateRequest
    ) {
        var course = courseService.updateCourse(courseUpdateRequest);
        return ResponseEntity.ok().body(course);
    }
}
