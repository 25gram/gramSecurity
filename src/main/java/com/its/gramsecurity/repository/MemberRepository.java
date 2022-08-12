package com.its.gramsecurity.repository;

import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Integer> {
      Optional<MemberEntity> findByLoginId(String LoginId);

      @Transactional
      @Modifying
      @Query(value = "update MemberEntity m set m.loginStatus=0 where m.loginId=:loginId")
    void logoutCheck(@Param("loginId") String loginId);


    @Transactional
      @Modifying
      @Query(value = "update MemberEntity m set m.loginStatus=1 where m.loginId=:loginId")
    void loginCheck(@Param("loginId") String loginId);

    Optional<MemberEntity> findByMemberEmail(String memberEmail);

    Optional<MemberEntity> findByMemberName(String memberName);

    List<MemberEntity> findByMemberNameContaining(MemberDTO memberDTO);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM  member_entity where memberName LIKE CONCAT('%',memberName,'%')",nativeQuery = true)
    List<MemberEntity> searchResult(String memberName);
}
