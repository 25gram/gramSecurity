package com.its.gramsecurity.repository;

import com.its.gramsecurity.dto.FollowDTO;
import com.its.gramsecurity.entity.FollowEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface FollowRepository extends JpaRepository<FollowEntity,Long> {


    List<FollowEntity> findAllByMyId(String myId);

    List<FollowEntity> findAllByYourId(String yourId);
    void deleteByYourIdAndMyId(String yourId, String myId);

    Optional<FollowEntity> findByMyIdAndYourId(String myId, String yourId);
}
