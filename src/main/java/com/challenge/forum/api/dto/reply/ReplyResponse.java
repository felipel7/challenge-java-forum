package com.challenge.forum.api.dto.reply;

import com.challenge.forum.domain.Reply;

public record ReplyResponse(
    Long id,
    String content,
    Boolean solution,
    Long topicId
) {
    public ReplyResponse(Reply reply) {
        this(
            reply.getId(),
            reply.getContent(),
            reply.getSolution(),
            reply.getTopic().getId()
        );
    }
}
