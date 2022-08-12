package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.RippleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RippleRepository extends JpaRepository<RippleEntity,Long> {

}
