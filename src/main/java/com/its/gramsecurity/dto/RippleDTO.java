package com.its.gramsecurity.dto;


import com.its.gramsecurity.entity.RippleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RippleDTO {
    private Long id;
    private Long commentId;
    private String rippleWriter;
    private String rippleContents;
    private LocalDateTime rippleCreatedTime;


    public static RippleDTO toFindDTO(RippleEntity rippleEntity){
        RippleDTO rippleDTO = new RippleDTO();
        rippleDTO.setId(rippleEntity.getId());
        rippleDTO.setCommentId(rippleEntity.getCommentId());
        rippleDTO.setRippleWriter(rippleEntity.getRippleWriter());
        rippleDTO.setRippleContents(rippleEntity.getRippleContents());
        return rippleDTO;
    }
}
