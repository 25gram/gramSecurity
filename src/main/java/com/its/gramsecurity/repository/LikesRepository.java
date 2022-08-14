package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.BoardEntity;
import com.its.gramsecurity.entity.CommentEntity;
import com.its.gramsecurity.entity.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;
@Repository
public interface LikesRepository extends JpaRepository<LikesEntity,Long> {
    Optional<LikesEntity> findByMemberNameAndBoardEntity(String memberName, BoardEntity boardEntity);
    Optional<LikesEntity> findByMemberNameAndCommentEntity(String memberName, CommentEntity commentEntity);
    @Transactional
    @Modifying
    @Query(value = "select l from LikesEntity l where l.id=:id AND l.memberName=:loginId AND l.boardEntity=:boardEntity")
    Optional<LikesEntity> boardLike(String loginId, BoardEntity boardEntity,Long id);

    @Transactional
    @Modifying
    @Query(value = "select l from LikesEntity l  where l.boardEntity=:boardEntity")
    List<LikesEntity> boardCount(List<BoardEntity> boardEntity);



    @Transactional
    @Modifying
    @Query(value = "select l from LikesEntity l where l.id=:id AND l.memberName=:loginId AND l.commentEntity=:commentEntity")
    Optional<LikesEntity> commentLike(String loginId,CommentEntity commentEntity, Long id);

    @Transactional
    @Modifying
    @Query(value = "select l from LikesEntity l where l.commentEntity=:commentEntity")
    List<LikesEntity> commentCount(List<CommentEntity> commentEntity);


    List<LikesEntity> findByMemberName(String loginId);
}
