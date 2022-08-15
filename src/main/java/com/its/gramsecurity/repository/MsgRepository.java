package com.its.gramsecurity.repository;

import com.its.gramsecurity.dto.MsgDTO;
import com.its.gramsecurity.entity.MsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MsgRepository extends JpaRepository<MsgEntity,Long> {
    List<MsgEntity> findByLoginId(String loginId);

    @Transactional
    @Modifying
    @Query(value = "select * from msgEntity where loginId=:loginId and friendId=:friendId or loginId=:friendId and friendId=:loginId",nativeQuery = true)
    List<MsgEntity> findList(@Param("loginId") String loginId,@Param("friendId")String friendId);

    @Transactional
    @Modifying
    @Query(value = "select * from msgEntity where loginId=:loginId group by friendId",nativeQuery = true)
    List<MsgEntity> findMsgList(String loginId);
}
