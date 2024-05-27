package com.challenge.forum.services;

import com.challenge.forum.api.dto.topic.TopicRequest;
import com.challenge.forum.api.dto.topic.TopicResponse;
import com.challenge.forum.api.dto.topic.TopicSummaryResponse;
import com.challenge.forum.domain.Topic;
import com.challenge.forum.repositories.CourseRepository;
import com.challenge.forum.repositories.TopicRepository;
import com.challenge.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public TopicResponse saveTopic(TopicRequest topicRequest) {
        var user = userRepository.getReferenceById(topicRequest.userId());
        var course = courseRepository.getReferenceById(topicRequest.courseId());

        var topicSaved = topicRepository.save(new Topic(topicRequest, user, course));
        return new TopicResponse(topicSaved);
    }

    public List<TopicSummaryResponse> findAll() {
        return topicRepository.findAll().stream().map(TopicSummaryResponse::new).toList();
    }
}
