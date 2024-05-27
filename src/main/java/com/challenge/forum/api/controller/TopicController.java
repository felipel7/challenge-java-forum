package com.challenge.forum.api.controller;

import com.challenge.forum.domain.Topic;
import com.challenge.forum.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping
    public ResponseEntity<List<Topic>> findAll() {
        var topics = topicRepository.findAll();
        return ResponseEntity.ok()
                             .body(topics);
    }
}
