package com.its.gramsecurity.dto;

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
}
