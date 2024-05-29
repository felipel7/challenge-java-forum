package com.challenge.forum.services;

import com.challenge.forum.api.dto.topic.TopicDetailsDto;
import com.challenge.forum.api.dto.topic.TopicDto;
import com.challenge.forum.api.dto.topic.TopicRequestDto;
import com.challenge.forum.api.dto.topic.TopicRequestUpdateDto;
import com.challenge.forum.api.utils.CopyUtils;
import com.challenge.forum.domain.Topic;
import com.challenge.forum.repositories.CourseRepository;
import com.challenge.forum.repositories.TopicRepository;
import com.challenge.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public TopicDetailsDto saveTopic(TopicRequestDto topicRequestDto) {
        var user = userRepository.getReferenceById(topicRequestDto.userId());
        var course = courseRepository.getReferenceById(topicRequestDto.courseId());
        var topic = topicRepository.save(new Topic(topicRequestDto, user, course));
        return new TopicDetailsDto(topic);
    }

    public Page<TopicDto> getTopicsPage(Pageable pageable) {
        var topics = topicRepository.findAllByActiveTrue(pageable);
        return topics.map(TopicDto::new);
    }

    public TopicDetailsDto getTopic(Long id) {
        var topic = topicRepository.getReferenceById(id);
        return new TopicDetailsDto(topic);
    }

    public TopicDetailsDto updateTopic(TopicRequestUpdateDto topicRequest) {
        var topic = topicRepository.getReferenceById(topicRequest.id());
        CopyUtils.copyNonNullProperties(topicRequest, topic);
        return new TopicDetailsDto(topic);
    }

    public void deleteTopic(Long id) {
        var topic = topicRepository.getReferenceById(id);
        topic.delete();
    }
}
