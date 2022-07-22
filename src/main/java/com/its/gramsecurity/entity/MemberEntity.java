package com.its.gramsecurity.entity;

import com.its.gramsecurity.dto.MemberDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "member_entity")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(length = 500)
    private String memberId;
    @Column(length = 100)
    private String memberPassword;
    @Column(length = 20)
    private String memberName;
    @Column(length = 30)
    private String memberEmail;
    @Column(length = 50)
    private String memberProfileName;
    @Column
    private int loginStatus;
    @Column(length = 500)
    private String memberIntro;
    @Column
    private String role;
    @Column
    private String provider;
    @Column
    private String providerId;

//    @Builder
//    public MemberEntity(String memberId, String memberPassword, String memberName, String memberEmail, String memberProfileName, String memberIntro, String role, String provider, String providerId) {
//        this.memberId = memberId;
//        this.memberPassword = memberPassword;
//        this.memberName = memberName;
//        this.memberEmail = memberEmail;
//        this.memberProfileName = memberProfileName;
//        this.memberIntro = memberIntro;
//        this.role = role;
//        this.provider = provider;
//        this.providerId = providerId;
//    }


    public static MemberEntity toSaveEntity(MemberDTO memberDTO){
        MemberEntity memberEntity=new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberProfileName(memberDTO.getMemberProfileName());
        memberEntity.setMemberIntro(memberDTO.getMemberIntro());
        memberEntity.setRole(memberDTO.getRole());
        memberEntity.setProvider(memberDTO.getProvider());
        memberEntity.setProviderId(memberDTO.getProviderId());
        return memberEntity;

    }



}
