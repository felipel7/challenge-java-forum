package com.challenge.forum.api.controller;

import com.challenge.forum.domain.Topic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @GetMapping
    public ResponseEntity<List<Topic>> findAll() {
        var topics = new ArrayList<Topic>(); // TODO: DELETE, JUST FOR TESTING PURPOSE
        return ResponseEntity.ok()
                             .body(topics);
    }
}
