package com.its.gramsecurity.entity;

import com.its.gramsecurity.dto.FollowDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Long myId;

    @Column(length = 100)
    private String yourId;

    @Column(length = 100)
    private String yourName;

    @Column(length = 100)
    private String yourProfileName;

    @Column
    private int loginStatus;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "memberId")
//    private MemberEntity memberEntity;

    public static FollowEntity toSaveEntity(FollowDTO followDTO) {
        FollowEntity followEntity=new FollowEntity();
        followEntity.setMyId(followDTO.getMyId());
        followEntity.setYourId(followDTO.getYourId());
        followEntity.setYourName(followDTO.getYourName());
        followEntity.setYourProfileName(followDTO.getYourProfileName());
        followEntity.setLoginStatus(followDTO.getLoginStatus());
        return followEntity;
    }
}
