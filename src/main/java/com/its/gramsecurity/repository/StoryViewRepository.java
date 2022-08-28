package com.its.gramsecurity.repository;

import com.its.gramsecurity.dto.StoryViewDTO;
import com.its.gramsecurity.entity.*;
import com.its.gramsecurity.entity.StoryViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryViewRepository extends JpaRepository<StoryViewEntity,Long> {
//    @Transactional
//    @Query(value = "select s from StoryViewEntity s where s.storyEntity.id=:id and s.storyEntity.loginId=:loginId")
    List<StoryViewEntity> findAllByStoryEntityIdAndMemberEntityLoginId(Long storyId, String storyWriter);

    Long countByStoryEntity_Id(Long storyId);

}
