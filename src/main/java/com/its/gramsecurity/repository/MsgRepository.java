package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.MsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsgRepository extends JpaRepository<MsgEntity,Long> {
}
