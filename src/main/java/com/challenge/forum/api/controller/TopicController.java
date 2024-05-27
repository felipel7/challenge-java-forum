package com.challenge.forum.api.controller;

import com.challenge.forum.api.dto.topic.TopicSummaryResponse;
import com.challenge.forum.api.dto.topic.TopicRequest;
import com.challenge.forum.api.dto.topic.TopicResponse;
import com.challenge.forum.services.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping
    public ResponseEntity<List<TopicSummaryResponse>> findAll() {
        var topics = topicService.findAll();
        return ResponseEntity.ok().body(topics);
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
