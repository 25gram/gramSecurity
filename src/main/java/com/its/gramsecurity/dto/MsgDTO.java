package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.MsgEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgDTO {

    Long MsgId;

    String loginId;

    String friendId;

    String text;

    int seeInt;

    LocalDateTime sendTime;

    LocalDateTime lastTime;

    String friendFileName;

    public static MsgDTO toDto(MsgEntity mem){
        MsgDTO dto=new MsgDTO();
        dto.setMsgId(mem.getMsgId());
        dto.setLoginId(mem.getLoginId());
        dto.setFriendId(mem.getFriendId());
        dto.setText(mem.getText());
        dto.setSeeInt(mem.getSeeInt());
        dto.setSendTime(mem.getSendTime());
        dto.setLastTime(mem.getLastTime());
        dto.setFriendFileName(mem.getFriendFileName());
        return dto;
    }


}
