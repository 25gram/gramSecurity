package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.FollowEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FollowRepository extends JpaRepository<FollowEntity,Long> {


//    List<FollowEntity> followList(Long id);
}
