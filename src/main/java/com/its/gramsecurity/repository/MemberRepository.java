package com.its.gramsecurity.repository;

import com.its.gramsecurity.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Integer> {
      Optional<MemberEntity> findByLoginId(String LoginId);

      @Transactional
      @Modifying
      @Query(value = "update MemberEntity m set m.loginStatus=0 where m.memberName=:memberName")
    void logoutCheck(@Param("memberName") String memberName);


    @Transactional
      @Modifying
      @Query(value = "update MemberEntity m set m.loginStatus=1 where m.memberName=:memberName")
    void loginCheck(@Param("memberName") String memberName);

    Optional<MemberEntity> findByMemberEmail(String memberEmail);

    Optional<MemberEntity> findByMemberName(String memberName);
}
