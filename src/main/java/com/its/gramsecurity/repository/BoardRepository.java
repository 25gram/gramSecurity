package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
//    Optional<BoardEntity> findByBoardId(Long boardId);
}
