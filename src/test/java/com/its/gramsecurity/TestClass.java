package com.its.gramsecurity;


import com.its.gramsecurity.dto.FollowDTO;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.FollowEntity;
import com.its.gramsecurity.entity.MemberEntity;
import com.its.gramsecurity.repository.FollowRepository;
import com.its.gramsecurity.repository.MemberRepository;
import com.its.gramsecurity.service.FollowService;
import com.its.gramsecurity.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.stream.IntStream;

@SpringBootTest
public class TestClass {
    @Autowired
    private MemberService memberService;

    @Autowired
    private FollowService followService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FollowRepository followRepository;


    public FollowDTO newFollow(int i) {
        FollowDTO followDTO = new FollowDTO(1L+i,"follow" + i, "follow" + i, "follow" + i,"follow"+i, "follow" + i, 1 + i);
        return followDTO;
    }
    public MemberDTO newMember(int i) {
        MemberDTO memberDTO = new MemberDTO("member"+i, "member" + i, "member" + i, 1 + i);
        return memberDTO;
    }


    @Test
    @Transactional
    public void memberSave(){
        IntStream.rangeClosed(1,20).forEach(i->{
            memberService.save(newMember(i));
        });
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("please....")
    public void followSaveTest(){
        FollowDTO followDTO=newFollow(3);
        MemberEntity memberEntity=memberRepository.findByMemberId("member").get();
        FollowEntity followEntity=FollowEntity.toSaveEntity(followDTO,memberEntity,"member");
        Long savedFollowId=followRepository.save(followEntity).getId();
    }



//    @Test
//    @Transactional
//    @DisplayName("연관관계 테스트")
//    @Rollback
//    public void followSaveTest(){
//        FollowDTO followDTO=newFollow(1);
//        MemberEntity memberEntity=memberRepository.findByMemberId("member1").get();
//        FollowEntity followEntity=FollowEntity.toSaveEntity(followDTO,memberEntity);
//    }

}
