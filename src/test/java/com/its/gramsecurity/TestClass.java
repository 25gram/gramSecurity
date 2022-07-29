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


    public FollowDTO newBoard(int i) {
        FollowDTO followDTO = new FollowDTO(1L+i,"11" + i, "11" + i, "11" + i,"11"+i, "11" + i, 1 + i);
        return followDTO;
    }




    @Test
    @Transactional
    @DisplayName("저장 테스트")
    @Rollback
    public void followSaveTest(){
    }

}
