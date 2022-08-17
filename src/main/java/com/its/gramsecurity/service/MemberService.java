package com.its.gramsecurity.service;

import com.its.gramsecurity.config.auth.PrincipalDetails;
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
    BoardService boardService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private MsgService msgService;

    @Autowired
    private FollowService followService;
    @Autowired
    private StoryService storyService;

    public MemberEntity save(MemberDTO memberDTO) throws IOException {
        MultipartFile memberProfile = memberDTO.getMemberProfile();
        String memberProfileName = memberProfile.getOriginalFilename();
        if (!memberProfile.isEmpty()) {
            String savePath = "C:\\springboot_img\\" + memberProfileName;
            memberProfileName = System.currentTimeMillis() + "_" + memberProfileName;
            memberProfile.transferTo(new File(savePath));
            memberDTO.setMemberProfileName(memberProfileName);
        } else {
            memberDTO.setMemberProfileName("noProfile.png");
        }
        return memberRepository.save(MemberEntity.toSaveEntity(memberDTO));

    }


    public MemberDTO findByMemberName(String memberName) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberName(memberName);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    @Transactional
    public void update(MemberDTO memberDTO) throws IOException {

        MemberEntity persistence = memberRepository.findByLoginId(memberDTO.getLoginId()).orElseThrow(() -> {
            return new IllegalArgumentException("회원찾기실패");
        });

        String rawPassword = memberDTO.getMemberPassword();
        if (rawPassword.isBlank()) {
            System.out.println("MemberService.update");
            System.out.println("rawPassword = " + rawPassword);
        } else {
            String encPassword = encoder.encode(rawPassword);
            persistence.setMemberPassword(encPassword);
        }
        MemberDTO findDTO = findByLoginId(memberDTO.getLoginId());
        persistence.setMemberIntro(memberDTO.getMemberIntro());
        persistence.setMemberName(memberDTO.getMemberName());

        MultipartFile memberFile = memberDTO.getMemberProfile();
        System.out.println(memberFile);
        String memberProfileName = memberFile.getOriginalFilename();
        memberProfileName = System.currentTimeMillis() + "_" + memberProfileName;
        persistence.setMemberProfileName(memberProfileName);
        if (!Objects.equals(findDTO.getMemberProfileName(), memberDTO.getMemberProfileName())) {
            if (!memberFile.isEmpty()) {
                memberProfileName = System.currentTimeMillis() + "_" + memberProfileName;
                String savePath = "C:\\springboot_img\\" + memberProfileName;
                memberFile.transferTo(new File(savePath));
                persistence.setMemberProfileName(memberProfileName);
            } else {
                memberDTO.setMemberProfileName(null);
            }
        } else if (findDTO.getMemberProfileName() == null) {
            if (!memberFile.isEmpty()) {
                memberProfileName = System.currentTimeMillis() + "_" + memberProfileName;
                String savePath = "C:\\springboot_img\\" + memberProfileName;
                memberFile.transferTo(new File(savePath));
                persistence.setMemberProfileName(memberProfileName);
            } else {
                persistence.setMemberProfileName(null);
            }
        }
        boardService.updateProfile(memberDTO, memberProfileName);
//        msgService.updateProfile(memberDTO, memberProfileName);
        followService.updateProfile(memberDTO,memberProfileName);
        storyService.updateProfile(memberDTO,memberProfileName);

    }

    public MemberDTO passwordCheck(MemberDTO memberDTO, PrincipalDetails principalDetails) {
        MemberDTO loginDTO = findByLoginId(principalDetails.getMemberDTO().getLoginId());
        String rawPassword = memberDTO.getMemberPassword();
        if (encoder.matches(rawPassword, loginDTO.getMemberPassword())) {
            return loginDTO;
        } else {
            return null;
        }
    }

    public MemberDTO findByLoginId(String LoginId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByLoginId(LoginId);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void delete(String loginId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByLoginId(loginId);
        memberRepository.delete(optionalMemberEntity.get());
    }

    public void loginCheck(String loginId) {
        memberRepository.loginCheck(loginId);
    }

    public void logoutCheck(String loginId) {
        memberRepository.logoutCheck(loginId);
    }

    public MemberEntity duplicateChkEmail(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isEmpty()) {
            return null;
        } else {
            return optionalMemberEntity.get();
        }
    }

    public MemberEntity duplicateChkId(String loginId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByLoginId(loginId);
        if (optionalMemberEntity.isEmpty()) {
            return null;
        } else {
            return optionalMemberEntity.get();
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity member : memberEntityList) {
            MemberDTO memberDTO = MemberDTO.toDTO(member);
            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    public List<MemberDTO> search(String search) {
        List<MemberEntity> memberEntityList = memberRepository.searchResult(search);
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toDTO(memberEntity));
        }
        return memberDTOList;

    }


}
