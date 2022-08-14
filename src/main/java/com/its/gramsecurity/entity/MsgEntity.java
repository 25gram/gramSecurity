package com.its.gramsecurity.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class MsgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long MsgId;

    @Column(length = 500)
    private String loginId;

    @Column(length = 500)
    private String friendId;

    @Column(length = 5000)
    String text;

    @Column()
    int seeInt;

    @Column()
    LocalDateTime sendTime;

    @Column()
    LocalDateTime lastTime;

    @Column()
    String friendFileName;




}
