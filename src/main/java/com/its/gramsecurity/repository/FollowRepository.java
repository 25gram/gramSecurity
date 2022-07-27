package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.FollowEntity;
import com.its.gramsecurity.entity.MemberEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<FollowEntity,Long> {

//    @Override
//    @EntityGraph(attributePaths = {"follow_entity"})
//    List<FollowEntity> findAll();
//
}
