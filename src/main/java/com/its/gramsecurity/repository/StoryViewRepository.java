package com.its.gramsecurity.repository;

import com.its.gramsecurity.dto.StoryViewDTO;
import com.its.gramsecurity.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryViewRepository extends JpaRepository<StoryViewEntity,Long> {

    boolean findByStoryIdANDLoginId(Long id, String loginId);

}
