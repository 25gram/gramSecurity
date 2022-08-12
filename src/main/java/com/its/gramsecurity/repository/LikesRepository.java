package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.BoardEntity;
import com.its.gramsecurity.entity.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LikesRepository extends JpaRepository<LikesEntity,Long> {
    Optional<LikesEntity> findByMemberNameAndBoardEntity(String memberName, BoardEntity boardEntity);


    List<LikesEntity> findByMemberName(String loginId);
}
