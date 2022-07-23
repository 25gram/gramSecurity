package com.its.gramsecurity.service;

import com.its.gramsecurity.repository.MemberRepository;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.MemberEntity;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;
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
    @Transactional
    public void update(MemberEntity memberEntity) {
        //수정시에 영속성 컨텍스트 User 오브젝트를 영속화 시키고, 영속화된 User 오브젝트를 수정
        //select 를 해서 User 오브젝트를 DB 로부터 가져오는 이유는 영속화를 하기 위해서
        //영속화된 오브젝트를 변경하면 자동으로 DB 에 update 문을 날려줌
        System.out.println("MemberService.update");
        System.out.println("memberEntity = " + memberEntity);
        MemberEntity persistence= memberRepository.findByMemberId(memberEntity.getMemberId()).orElseThrow(()->{
            return new IllegalArgumentException("회원찾기실패");
        });
        String rawPassword=memberEntity.getMemberPassword();
        String encPassword=encoder.encode(rawPassword);
        persistence.setMemberPassword(encPassword);
        persistence.setMemberIntro(memberEntity.getMemberIntro());


    }
}
