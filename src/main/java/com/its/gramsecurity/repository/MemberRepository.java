package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Integer> {
      Optional<MemberEntity> findByMemberId(String memberId);
}