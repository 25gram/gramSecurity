package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.BaseEntity;
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
public class MsgDTO extends BaseEntity {

    public Long MsgId;

    public String loginId;

    public String friendId;

    public String text;

    public int seeInt;

    public String friendFileName;
    public String loginFileName;

    public String friendName;
    public String loginName;


    public static MsgDTO toDto(MsgEntity mem){
        MsgDTO dto=new MsgDTO();
        dto.setMsgId(mem.getMsgId());
        dto.setLoginId(mem.getLoginId());
        dto.setFriendId(mem.getFriendId());
        dto.setText(mem.getText());
        dto.setSeeInt(mem.getSeeInt());
        dto.setFriendFileName(mem.getFriendFileName());
        dto.setFriendName(mem.getFriendName());
        dto.setLoginName(mem.getLoginName());
        dto.setLoginFileName(mem.getLoginFileName());
        return dto;
    }
    public static MsgDTO toSaveDto(MsgEntity mem){
        MsgDTO dto=new MsgDTO();
        dto.setLoginId(mem.getLoginId());
        dto.setFriendId(mem.getFriendId());
        dto.setText(mem.getText());
        dto.setSeeInt(mem.getSeeInt());
        dto.setFriendFileName(mem.getFriendFileName());
        dto.setMsgId(mem.getMsgId());
        dto.setFriendName(mem.getFriendName());
        dto.setLoginName(mem.getLoginName());
        dto.setLoginFileName(mem.getLoginFileName());
        return dto;
    }


}
