package com.challenge.forum.api.dto.reply;

public record ReplyCreateRequest(
    String content,
    Long topicId,
    Long userId
) {
}