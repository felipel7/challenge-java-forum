package com.challenge.forum.services;

import com.challenge.forum.api.dto.topic.TopicRequest;
import com.challenge.forum.api.dto.topic.TopicDto;
import com.challenge.forum.api.dto.topic.TopicDetailsDto;
import com.challenge.forum.domain.Topic;
import com.challenge.forum.repositories.UserRepository;
import com.challenge.forum.repositories.CourseRepository;
import com.challenge.forum.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public TopicDetailsDto saveTopic(TopicRequest topicRequest) {
        var user = userRepository.getReferenceById(topicRequest.userId());
        var course = courseRepository.getReferenceById(topicRequest.courseId());

        var topic = topicRepository.save(new Topic(topicRequest, user, course));
        return new TopicDetailsDto(topic);
    }

    public Page<TopicDto> getTopicsPage(Pageable pageable) {
        var topics = topicRepository.findAllByActiveTrue(pageable);
        return topics.map(TopicDto::new);
    }

    public TopicDetailsDto getTopic(Long id) {
        var topic = topicRepository.findById(id).orElseThrow(RuntimeException::new);
        return new TopicDetailsDto(topic);
    }
}
