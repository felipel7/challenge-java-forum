package com.challenge.forum.api.dto.topic;

import com.challenge.forum.domain.Topic;

import java.time.LocalDateTime;

public record TopicResponse(
    Long id,
    String title,
    String content,
    LocalDateTime createdAt
) {
    public TopicResponse(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getContent(), topic.getCreatedAt());
    }
}
