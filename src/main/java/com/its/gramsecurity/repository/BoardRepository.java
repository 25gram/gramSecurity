package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    @Transactional
    @Modifying
    @Query(value = "update BoardEntity b set b.likes=1 where b.id=:id")
    void likes(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update BoardEntity b set b.likes=null where b.id=:id")
    void likesDelete(@Param("id") Long id);


    List<BoardEntity> findByLoginId(String loginId);

}
