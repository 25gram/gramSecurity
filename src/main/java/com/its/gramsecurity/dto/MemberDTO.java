package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.MemberEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String loginId;
    private String memberPassword;
    private String memberEmail;
    private String memberName;
    private MultipartFile memberProfile;
    private String memberProfileName;
    private int loginStatus;
    private String memberIntro;
    private String role;
    private String provider;
    private String providerId;

    @Builder
    public MemberDTO(String loginId, String memberPassword,String memberName, String memberEmail, String memberProfileName,MultipartFile memberProfile, int loginStatus, String memberIntro, String role, String provider, String providerId) {
        this.loginId = loginId;
        this.memberPassword = memberPassword;
        this.memberName=memberName;
        this.memberEmail = memberEmail;
        this.memberProfileName = memberProfileName;
        this.memberProfile=memberProfile;
        this.loginStatus = loginStatus;
        this.memberIntro = memberIntro;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public MemberDTO(String loginId, String memberName, String memberProfileName, int loginStatus) {
        this.loginId = loginId;
        this.memberName = memberName;
        this.memberProfileName = memberProfileName;
        this.loginStatus = loginStatus;
    }


    public static MemberDTO toDTO(MemberEntity memberEntity){
        MemberDTO memberDTO=new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setLoginId(memberEntity.getLoginId());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberProfileName(memberEntity.getMemberProfileName());
        memberDTO.setMemberIntro(memberEntity.getMemberIntro());
        memberDTO.setLoginStatus(memberEntity.getLoginStatus());
        memberDTO.setRole(memberEntity.getRole());
        memberDTO.setProvider(memberEntity.getProvider());
        memberDTO.setProviderId(memberEntity.getProviderId());
        return memberDTO;



    }

}
