package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.FollowEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FollowRepository extends JpaRepository<FollowEntity,Long> {

}
