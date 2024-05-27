package com.challenge.forum.domain;

import com.challenge.forum.api.dto.topic.TopicRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_ANSWERED;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "topic")
    private List<Reply> replies = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Topic(TopicRequest topicRequest, User user, Course course) {
        var date = LocalDateTime.now();
        this.title = topicRequest.title();
        this.content = topicRequest.content();
        this.active = true;
        this.status = Status.NOT_ANSWERED;
        this.user = user;
        this.course = course;
        this.createdAt = date;
        this.updatedAt = date;
    }
}
