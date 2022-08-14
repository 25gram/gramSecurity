package com.its.gramsecurity.entity;

import com.its.gramsecurity.dto.MemberDTO;
import jdk.jfr.Name;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member_entity")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(length = 500)
    private String loginId;
    @Column(length = 100)
    private String memberPassword;
    @Column(length = 100)
    private String memberName;
    @Column(length = 30)
    private String memberEmail;
    @Column(length = 500)
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

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = false,fetch = FetchType.LAZY)
    private List<BoardEntity>boardEntityList=new ArrayList<>();

    @OneToMany(mappedBy ="memberEntity" ,cascade =CascadeType.REMOVE,orphanRemoval = false,fetch = FetchType.LAZY)
    private List<FollowEntity> followEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = false, fetch = FetchType.LAZY)
    private List<CommentEntity>commentEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = false, fetch = FetchType.LAZY)
    private List<LikesEntity>likesEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = false, fetch = FetchType.LAZY)
    private List<RippleEntity>rippleEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = false, fetch = FetchType.LAZY)
    private List<MsgEntity>msgEntityList=new ArrayList<>();
    public static MemberEntity toSaveEntity(MemberDTO memberDTO){
        MemberEntity memberEntity=new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setLoginId(memberDTO.getLoginId());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberProfileName(memberDTO.getMemberProfileName());
        memberEntity.setMemberIntro(memberDTO.getMemberIntro());
        memberEntity.setLoginStatus(memberDTO.getLoginStatus());
        memberEntity.setRole(memberDTO.getRole());
        memberEntity.setProvider(memberDTO.getProvider());
        memberEntity.setProviderId(memberDTO.getProviderId());
        return memberEntity;

    }

    @Builder
    public MemberEntity(String loginId, String memberPassword,String memberName, String memberEmail, String memberProfileName, int loginStatus, String memberIntro, String role, String provider, String providerId) {
        this.loginId = loginId;
        this.memberPassword = memberPassword;
        this.memberName=memberName;
        this.memberEmail = memberEmail;
        this.memberProfileName = memberProfileName;
        this.loginStatus = loginStatus;
        this.memberIntro = memberIntro;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }
}