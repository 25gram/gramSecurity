package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoryRepository extends JpaRepository<StoryEntity, Long> {
    List<StoryEntity> findByLoginId(String loginId);

    List<StoryEntity> findByStoryLocationTag(String storyLocationTag);

}

