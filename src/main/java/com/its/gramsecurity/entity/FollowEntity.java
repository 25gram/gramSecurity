package com.its.gramsecurity.entity;

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

    @Column (length = 100)
    private String myId;

    @Column(length = 100)
    private String yourId;

    @Column(length = 100)
    private String yourName;

    @Column(length = 100)
    private String yourProfileName;

    @Column
    private int loginStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private MemberEntity memberEntity;
}
