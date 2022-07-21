package com.its.gramsecurity.service;

import com.its.gramsecurity.Repository.MemberRepository;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity= MemberEntity.toSaveEntity(memberDTO);
        memberRepository.save(memberEntity);
    }
}
