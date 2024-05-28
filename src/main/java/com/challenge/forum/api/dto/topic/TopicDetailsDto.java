package com.challenge.forum.api.dto.topic;

import com.challenge.forum.domain.Reply;
import com.challenge.forum.domain.Status;
import com.challenge.forum.domain.Topic;

import java.time.LocalDateTime;
import java.util.List;

public record TopicDetailsDto(
    Long id,
    String title,
    String content,
    Status status,
    List<Reply> replies,
    LocalDateTime createdAt
) {
    public TopicDetailsDto(Topic topic) {
        this(
            topic.getId(),
            topic.getTitle(),
            topic.getContent(),
            topic.getStatus(),
            topic.getReplies(),
            topic.getCreatedAt()
        );
    }
}
