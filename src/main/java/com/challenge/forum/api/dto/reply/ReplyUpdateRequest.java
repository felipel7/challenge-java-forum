package com.challenge.forum.api.dto.reply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReplyUpdateRequest(
    @NotNull
    Long id,

    @NotBlank
    String content,

    Boolean solution
) {
}
