package com.challenge.forum.domain;

import com.challenge.forum.api.dto.course.CourseRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    @OneToMany(mappedBy = "course")
    private List<Topic> topics = new ArrayList<>();

    public Course(CourseRequest courseRequest) {
        this.name = courseRequest.name();
        this.category = courseRequest.category();
    }
}
