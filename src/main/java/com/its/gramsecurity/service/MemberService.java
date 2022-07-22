package com.its.gramsecurity.service;

import com.its.gramsecurity.repository.MemberRepository;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity= MemberEntity.toSaveEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO findByMemberId(String memberId) {
        Optional<MemberEntity>optionalMemberEntity=memberRepository.findByMemberId(memberId);
        if(optionalMemberEntity.isPresent()){
            return MemberDTO.toDTO(optionalMemberEntity.get());
        }else{
            return null;
        }
    }
}
