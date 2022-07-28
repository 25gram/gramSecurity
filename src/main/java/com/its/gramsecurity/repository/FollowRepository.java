package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.FollowEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FollowRepository extends JpaRepository<FollowEntity,Long> {


    List<Optional<FollowEntity>> findAllByMyId(Long myId);
}
