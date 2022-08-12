package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<StoryEntity, Long> {
    List<StoryEntity> findByMemberEntity_Id(Long memberId);

    List<StoryEntity> findByStoryLocationTag(String storyLocationTag);
}

