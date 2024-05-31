package com.challenge.forum.services;

import com.challenge.forum.api.dto.topic.TopicCreateRequest;
import com.challenge.forum.api.dto.topic.TopicDetailsResponse;
import com.challenge.forum.api.dto.topic.TopicResponse;
import com.challenge.forum.api.dto.topic.TopicUpdateRequest;
import com.challenge.forum.api.utils.CopyUtils;
import com.challenge.forum.domain.Topic;
import com.challenge.forum.exceptions.businessExceptions.DuplicateTopicException;
import com.challenge.forum.repositories.CourseRepository;
import com.challenge.forum.repositories.TopicRepository;
import com.challenge.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.challenge.forum.api.utils.Constants.*;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public TopicDetailsResponse saveTopic(TopicCreateRequest topicCreateRequest) {
        var user = userRepository.getReferenceById(topicCreateRequest.userId());
        var course = courseRepository.getReferenceById(topicCreateRequest.courseId());
        var topic = new Topic(topicCreateRequest);
        validateTopic(topic);
        topic.setCourse(course);
        topic.setUser(user);
        topicRepository.save(topic);
        return new TopicDetailsResponse(topic);
    }

    public Page<TopicResponse> getTopicsPage(Pageable pageable) {
        var topics = topicRepository.findAllByActiveTrue(pageable);
        return topics.map(TopicResponse::new);
    }

    public TopicDetailsResponse getTopic(Long id) {
        var topic = topicRepository.getReferenceById(id);
        return new TopicDetailsResponse(topic);
    }

    public TopicDetailsResponse updateTopic(TopicUpdateRequest topicRequest) {
        var topic = topicRepository.getReferenceById(topicRequest.id());
        CopyUtils.copyNonNullProperties(topicRequest, topic);
        return new TopicDetailsResponse(topic);
    }

    public void deleteTopic(Long id) {
        var topic = topicRepository.getReferenceById(id);
        topic.delete();
    }

    private void validateTopic(Topic topic) {
        var alreadyExists = topicRepository.existsByTitleAndContent(topic.getTitle(), topic.getContent());
        if (alreadyExists) throw new DuplicateTopicException(TOPIC_ALREADY_REGISTERED);
    }
}
