package com.its.gramsecurity.Repository;

import com.its.gramsecurity.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Integer> {
    MemberEntity findByMemberId(String memberId);
}
