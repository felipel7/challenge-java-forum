package com.challenge.forum.api.controller;

import com.challenge.forum.api.dto.topic.TopicCreateRequest;
import com.challenge.forum.api.dto.topic.TopicDetailsResponse;
import com.challenge.forum.api.dto.topic.TopicResponse;
import com.challenge.forum.api.dto.topic.TopicUpdateRequest;
import com.challenge.forum.services.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static com.challenge.forum.api.utils.Constants.*;

@RestController
@RequestMapping(TOPIC_CONTROLLER_ROUTE_MAP)
@Tag(name = DOCS_TOPIC_TAG_NAME)
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public ResponseEntity<Page<TopicResponse>> getTopics(
        @PageableDefault(size = 10) Pageable pageable
    ) {
        var page = topicService.getTopicsPage(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping(GET_DETAILS_TOPIC_ROUTE)
    public ResponseEntity<TopicDetailsResponse> getTopic(@PathVariable Long id) {
        var topic = topicService.getTopic(id);
        return ResponseEntity.ok(topic);
    }

    @PostMapping
    @Transactional
    @SecurityRequirement(name = DOCS_BEARER_KEY)
    public ResponseEntity<TopicDetailsResponse> saveTopic(
        @Valid @RequestBody TopicCreateRequest topicCreateRequest,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        var topic = topicService.saveTopic(topicCreateRequest);
        var uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.id()).toUri();
        return ResponseEntity.created(uri).body(topic);
    }

    @PutMapping
    @Transactional
    @SecurityRequirement(name = DOCS_BEARER_KEY)
    public ResponseEntity<TopicDetailsResponse> updateTopic(
        @Valid @RequestBody TopicUpdateRequest topicUpdateRequest
    ) {
        var topic = topicService.updateTopic(topicUpdateRequest);
        return ResponseEntity.ok(topic);
    }

    @DeleteMapping(DELETE_TOPIC_ROUTE)
    @Transactional
    @SecurityRequirement(name = DOCS_BEARER_KEY)
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

}
