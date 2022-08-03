package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LikesRepository extends JpaRepository<LikesEntity,Long> {
    Optional<LikesEntity> findByMemberIdAndBoardId(String memberId,Long boardId);
}
