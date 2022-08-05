package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
    List<BoardFileEntity> findByBoardId(Long boardId);
}
