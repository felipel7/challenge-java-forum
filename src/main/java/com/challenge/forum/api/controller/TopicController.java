package com.challenge.forum.api.controller;

import com.challenge.forum.api.dto.topic.TopicRequest;
import com.challenge.forum.api.dto.topic.TopicResponse;
import com.challenge.forum.api.dto.topic.TopicSummaryResponse;
import com.challenge.forum.services.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping
    public ResponseEntity<Page<TopicSummaryResponse>> findAll(
        @PageableDefault(size = 10)
        Pageable pageable
    ) {
        var page = topicService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponse> create(
        @Valid
        @RequestBody
        TopicRequest topicRequest,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        var topic = topicService.saveTopic(topicRequest);
        var uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.id()).toUri();

        return ResponseEntity.created(uri).body(topic);
    }
}
