package com.challenge.forum.api.dto.topic;

import com.challenge.forum.api.dto.reply.ReplyResponse;
import com.challenge.forum.domain.Status;
import com.challenge.forum.domain.Topic;

import java.time.LocalDateTime;
import java.util.List;

public record TopicDetailsResponse(
    Long id,
    String title,
    String content,
    Status status,
    List<ReplyResponse> replies,
    LocalDateTime createdAt
) {
    public TopicDetailsResponse(Topic topic) {
        this(
            topic.getId(),
            topic.getTitle(),
            topic.getContent(),
            topic.getStatus(),
            topic.getReplies().stream().map(ReplyResponse::new).toList(),
            topic.getCreatedAt()
        );
    }
}
