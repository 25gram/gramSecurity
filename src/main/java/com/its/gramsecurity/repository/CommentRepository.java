package com.its.gramsecurity.repository;


import com.its.gramsecurity.entity.BoardEntity;
import com.its.gramsecurity.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

    @Transactional
    @Modifying
    @Query(value = "update CommentEntity c set c.likes=1 where c.id=:id")
    void likes(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update CommentEntity c set c.likes=null where c.id=:id")
    void likesDelete(@Param("id") Long id);

}
