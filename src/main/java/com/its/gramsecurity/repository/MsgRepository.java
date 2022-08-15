package com.its.gramsecurity.repository;

import com.its.gramsecurity.dto.MsgDTO;
import com.its.gramsecurity.entity.MsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MsgRepository extends JpaRepository<MsgEntity,Long> {
    List<MsgEntity> findByFriendId(String friendId);
}
