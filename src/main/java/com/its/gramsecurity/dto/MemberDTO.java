package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.MemberEntity;
import lombok.Data;

@Data
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

    public static MemberDTO toDTO(MemberEntity memberEntity){
        MemberDTO memberDTO=new MemberDTO();
        memberDTO.setId(memberEntity.getId());
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
