package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.CommentEntity;
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
    private Long boardId;
    private Long commentId;
    private String rippleWriter;
    private String rippleContents;
    private LocalDateTime rippleCreatedTime;


    public static RippleDTO toFindDTO(RippleEntity rippleEntity){
        RippleDTO rippleDTO = new RippleDTO();
        rippleDTO.setId(rippleEntity.getId());
        rippleDTO.setBoardId(rippleEntity.getBoardEntity().getId());
        rippleDTO.setCommentId(rippleEntity.getCommentEntity().getId());
        rippleDTO.setRippleWriter(rippleEntity.getMemberEntity().getMemberName());
        rippleDTO.setRippleContents(rippleEntity.getRippleContents());
        return rippleDTO;
    }
}
