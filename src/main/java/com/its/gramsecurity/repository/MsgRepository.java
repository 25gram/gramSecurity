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

    //    @Transactional
//    @Modifying
//    @Query(value = "select * from msgEntity where loginId=:loginId or friendId=:loginId",nativeQuery = true)
//    List<MsgEntity> findMsgList(@Param("loginId") String loginId);
//}
    @Transactional
    @Modifying
//@Query(value = "select * from msgEntity where loginId=:loginId or friendId=:loginId",nativeQuery = true)
//@Query(value = "select * from msgEntity where createdTime in (SELECT max(createdTime) FROM msgentity where loginId=:loginId or friendId=:loginId group by friendId) order by createdTime desc",nativeQuery = true)
    @Query(value = "select * from msgentity where createdTime in (SELECT max(createdTime) FROM msgentity where loginId=:loginId and friendId=:friendId or loginId=:friendId and friendId=:loginId );",nativeQuery = true)
    List<MsgEntity> findMsgList(@Param("loginId") String loginId,@Param("friendId") String friendId);

    //@Transactional
//@Modifying
//@Query(value = "select count(text) from msgEntity where loginId=:loginId and friendId=:friendId or loginId=:friendId and friendId=:loginId",nativeQuery = true)
//Long count(@Param("loginId") String loginId,@Param("friendId") String friendId);
//}
    @Transactional
    @Modifying
    @Query(value = "select * from msgEntity where loginId=:loginId and friendId=:friendId or loginId=:friendId and friendId=:loginId",nativeQuery = true)
    List<MsgEntity> count(@Param("loginId") String loginId,@Param("friendId") String friendId);

    @Transactional
    @Modifying
    @Query(value = "select * from msgEntity where loginId=:loginId or friendId=:loginId",nativeQuery = true)
    List<MsgEntity> total(@Param("loginId") String loginId);

    @Transactional
    @Modifying
    @Query(value = "select * from msgEntity where loginId=:loginId group by friendId ",nativeQuery = true)
    List<MsgEntity> findLeft(String loginId);
    @Transactional
    @Modifying
    @Query(value = "select * from msgEntity where friendId=:loginId group by loginId ",nativeQuery = true)
    List<MsgEntity> findLeft1(String loginId);
}
//select * from msgentity where createdTime in (SELECT max(createdTime) FROM msgentity where loginId='hasangsu82' or friendId='hasangsu82' group by friendId);