package com.its.gramsecurity.repository;


import com.its.gramsecurity.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository  extends JpaRepository<CommentEntity,Long> {


    List<CommentEntity> findAllById(Long boardId);
}
