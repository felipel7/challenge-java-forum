package com.challenge.forum.api.controller;

import com.challenge.forum.api.dto.reply.ReplyCreateRequest;
import com.challenge.forum.api.dto.reply.ReplyResponse;
import com.challenge.forum.api.dto.reply.ReplyUpdateRequest;
import com.challenge.forum.services.ReplyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.challenge.forum.api.utils.Constants.*;

@RestController
@RequestMapping(REPLY_CONTROLLER_ROUTE_MAP)
@Tag(name = DOCS_REPLY_TAG_NAME)
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping(GET_REPLIES_FROM_TOPIC_ROUTE)
    public ResponseEntity<List<ReplyResponse>> getAllRepliesFromTopic(@PathVariable Long id) {
        var replies = replyService.getAllReplies(id);
        return ResponseEntity.ok().body(replies);
    }

    @PostMapping
    @Transactional
    @SecurityRequirement(name = DOCS_BEARER_KEY)
    public ResponseEntity<ReplyResponse> saveReply(
        @Valid @RequestBody ReplyCreateRequest replyCreateRequest,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        var reply = replyService.saveReply(replyCreateRequest);
        var uri = uriComponentsBuilder.path("/replies/{id}").buildAndExpand(reply.id()).toUri();
        return ResponseEntity.created(uri).body(reply);
    }

    @PutMapping
    @Transactional
    @SecurityRequirement(name = DOCS_BEARER_KEY)
    public ResponseEntity<ReplyResponse> updateReply(
        @Valid @RequestBody ReplyUpdateRequest replyUpdateRequest
    ) {
        var reply = replyService.updateReply(replyUpdateRequest);
        return ResponseEntity.ok().body(reply);
    }

    @DeleteMapping(DELETE_REPLY_ROUTE)
    @Transactional
    @SecurityRequirement(name = DOCS_BEARER_KEY)
    public ResponseEntity deleteReply(
        @PathVariable Long id
    ) {
        replyService.deleteReply(id);
        return ResponseEntity.noContent().build();
    }
}
