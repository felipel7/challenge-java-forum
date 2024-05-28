package com.challenge.forum.api.controller;

import com.challenge.forum.api.dto.topic.TopicDetailsDto;
import com.challenge.forum.api.dto.topic.TopicDto;
import com.challenge.forum.api.dto.topic.TopicRequest;
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
    public ResponseEntity<Page<TopicDto>> getTopics(@PageableDefault(size = 5) Pageable pageable
    ) {
        var page = topicService.getTopicsPage(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailsDto> getTopic(@PathVariable Long id) {
        var topic = topicService.getTopic(id);
        return ResponseEntity.ok(topic);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDetailsDto> create(@Valid @RequestBody TopicRequest topicRequest,
                                                  UriComponentsBuilder uriComponentsBuilder
    ) {
        var topic = topicService.createTopic(topicRequest);
        var uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.id()).toUri();

        return ResponseEntity.created(uri).body(topic);
    }
}
