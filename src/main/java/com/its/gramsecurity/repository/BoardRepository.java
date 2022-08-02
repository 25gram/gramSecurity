package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.BoardEntity;
import com.its.gramsecurity.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    List<Optional<CommentEntity>> findAllById(Long boardId);
//    Optional<BoardEntity> findByBoardId(Long boardId);



}
