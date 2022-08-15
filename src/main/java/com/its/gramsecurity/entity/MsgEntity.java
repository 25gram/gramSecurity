package com.its.gramsecurity.entity;

import com.its.gramsecurity.dto.MsgDTO;
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
public class MsgEntity extends BaseEntity{
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

    @Column
    String friendName;

    @Column()
    String friendFileName;

     public static MsgEntity toEntity(MsgDTO mem){
        MsgEntity dto=new MsgEntity();
        dto.setLoginId(mem.getLoginId());
        dto.setFriendId(mem.getFriendId());
        dto.setText(mem.getText());
        dto.setSeeInt(mem.getSeeInt());
        dto.setFriendFileName(mem.getFriendFileName());
        dto.setFriendName(mem.getFriendName());
        return dto;
    }


}
