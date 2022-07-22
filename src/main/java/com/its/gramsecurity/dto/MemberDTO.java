package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.MemberEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String memberId;
    private String memberPassword;
    private String memberEmail;
    private String memberProfileName;
    private int loginStatus;
    private String memberIntro;
    private String role;
    private String provider;
    private String providerId;

    @Builder
    public MemberDTO(String memberId, String memberPassword, String memberEmail, String memberProfileName, int loginStatus, String memberIntro, String role, String provider, String providerId) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberEmail = memberEmail;
        this.memberProfileName = memberProfileName;
        this.loginStatus = loginStatus;
        this.memberIntro = memberIntro;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }




    public static MemberDTO toDTO(MemberEntity memberEntity){
        MemberDTO memberDTO=new MemberDTO();
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberProfileName(memberEntity.getMemberProfileName());
        memberDTO.setMemberIntro(memberEntity.getMemberIntro());
        memberDTO.setRole(memberEntity.getRole());
        memberDTO.setProvider(memberEntity.getProvider());
        memberDTO.setProviderId(memberDTO.getProviderId());
        return memberDTO;



    }

}
