package com.challenge.forum.api.dto.topic;

import com.challenge.forum.domain.Topic;

import java.time.LocalDateTime;

public record TopicSummaryResponse(
    Long id,
    String title,
    String content,
    LocalDateTime createdAt
) {
    public TopicSummaryResponse(Topic topic) {
        this(
            topic.getId(),
            topic.getTitle(),
            topic.getContent(),
            topic.getCreatedAt()
        );
    }
}
