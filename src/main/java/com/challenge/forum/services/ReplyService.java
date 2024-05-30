package com.challenge.forum.services;

import com.challenge.forum.api.dto.reply.ReplyCreateRequest;
import com.challenge.forum.api.dto.reply.ReplyResponse;
import com.challenge.forum.api.dto.reply.ReplyUpdateRequest;
import com.challenge.forum.api.utils.CopyUtils;
import com.challenge.forum.domain.Reply;
import com.challenge.forum.repositories.ReplyRepository;
import com.challenge.forum.repositories.TopicRepository;
import com.challenge.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ReplyResponse> getAllReplies(Long id) {
        var topic = topicRepository.getReferenceById(id);
        return topic.getReplies().stream().map(ReplyResponse::new).toList();
    }

    public ReplyResponse saveReply(ReplyCreateRequest replyCreateRequest) {
        var topic = topicRepository.getReferenceById(replyCreateRequest.topicId());
        var user = userRepository.getReferenceById(replyCreateRequest.userId());
        var reply = new Reply(replyCreateRequest);
        reply.setUser(user);
        reply.setTopic(topic);
        replyRepository.save(reply);
        return new ReplyResponse(reply);
    }

    public ReplyResponse updateReply(ReplyUpdateRequest replyUpdateRequest) {
        var reply = replyRepository.getReferenceById(replyUpdateRequest.id());
        CopyUtils.copyNonNullProperties(replyUpdateRequest, reply);
        return new ReplyResponse(reply);
    }

    public void deleteReply(Long id) {
        var reply = replyRepository.getReferenceById(id);
        replyRepository.delete(reply);
    }
}
