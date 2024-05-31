package com.challenge.forum.repositories;

import com.challenge.forum.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findAllByActiveTrue(Pageable pageable);

    Boolean existsByTitleAndContent(String title, String content);
}
