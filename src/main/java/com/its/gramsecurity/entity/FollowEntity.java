package com.its.gramsecurity.entity;

import com.its.gramsecurity.dto.FollowDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.security.Principal;

@Entity
@Data
@NoArgsConstructor
@Table(name="follow_entity")
public class FollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Long id;

    @Column
    private String myId;

    @Column
    private String myName;

    @Column
    private String yourId;

    @Column(length = 100)
    private String yourName;

    @Column(length = 100)
    private String yourProfileName;

    @Column
    private String myProfileName;

    @Column
    private int loginStatus;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    public static FollowEntity toSaveEntity(FollowDTO followDTO, MemberEntity memberEntity,String myId) {
        FollowEntity followEntity=new FollowEntity();
        followEntity.setMyId(myId);
        followEntity.setMyName(followDTO.getMyName());
        followEntity.setYourId(followDTO.getYourId());
        followEntity.setYourName(followDTO.getYourName());
        followEntity.setYourProfileName(followDTO.getYourProfileName());
        followEntity.setMyProfileName(followDTO.getMyProfileName());
        followEntity.setLoginStatus(memberEntity.getLoginStatus());
        followEntity.setMemberEntity(memberEntity);
        return followEntity;
    }

}
