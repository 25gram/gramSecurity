package com.its.gramsecurity.service;

import com.its.gramsecurity.repository.MemberRepository;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO findByMemberId(String memberId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberId);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    @Transactional
    public void update(MemberDTO memberDTO) throws IOException {

        MemberEntity persistence = memberRepository.findByMemberId(memberDTO.getMemberId()).orElseThrow(() -> {
            return new IllegalArgumentException("회원찾기실패");
        });
        String rawPassword = memberDTO.getMemberPassword();
        String encPassword = encoder.encode(rawPassword);
        persistence.setMemberPassword(encPassword);
        MemberDTO findDTO = findByMemberId(memberDTO.getMemberId());
        persistence.setMemberIntro(memberDTO.getMemberIntro());
        persistence.setMemberName(memberDTO.getMemberName());

        MultipartFile memberFile = memberDTO.getMemberProfile();
        System.out.println(memberFile);
        String memberProfileName = memberFile.getOriginalFilename();
        memberProfileName = System.currentTimeMillis() + "_" + memberProfileName;
        if (!Objects.equals(findDTO.getMemberProfileName(), memberDTO.getMemberProfileName())) {
            if (!memberFile.isEmpty()) {
                memberProfileName = System.currentTimeMillis() + "_" + memberProfileName;
                String savePath = "D:\\springboot_img\\" + memberProfileName;
                memberFile.transferTo(new File(savePath));
                persistence.setMemberProfileName(memberProfileName);
            } else {
                memberDTO.setMemberProfileName(null);
            }
        } else if (findDTO.getMemberProfileName() == null) {
            if (!memberFile.isEmpty()) {
                memberProfileName = System.currentTimeMillis() + "_" + memberProfileName;
                String savePath = "D:\\springboot_img\\" + memberProfileName;
                memberFile.transferTo(new File(savePath));
                persistence.setMemberProfileName(memberProfileName);
            } else {
                persistence.setMemberProfileName(null);
            }
        }


    }

    public void delete(String memberId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberId);
        memberRepository.delete(optionalMemberEntity.get());
    }

    public void loginCheck(String memberId) {
        memberRepository.loginCheck(memberId);
    }

    public void logoutCheck(String memberId) {
        memberRepository.logoutCheck(memberId);
    }

    public MemberEntity duplicateChkEmail(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isEmpty()) {
            return null;
        } else {
            return optionalMemberEntity.get();
        }
    }

    public MemberEntity duplicateChkId(String memberId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberId);
        if (optionalMemberEntity.isEmpty()) {
            return null;
        } else {
            return optionalMemberEntity.get();
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity>memberEntityList=memberRepository.findAll();
        List<MemberDTO>memberDTOList=new ArrayList<>();
        for(MemberEntity member:memberEntityList){
            MemberDTO memberDTO=MemberDTO.toDTO(member);
            memberDTOList.add(memberDTO);
        }return memberDTOList;
    }
}
