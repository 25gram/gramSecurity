package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.BoardEntity;
import com.its.gramsecurity.entity.LikesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikesDTO {
    private Long id;
    private String memberName;
    private Long boardId;
//    private Long commentId;
    private Integer likes;

    public static LikesDTO toLikeSave(LikesEntity likesEntity){
        LikesDTO likesDTO = new LikesDTO();
        likesDTO.setId(likesEntity.getId());
        likesDTO.setMemberName(likesEntity.getMemberName());
        likesDTO.setBoardId(likesEntity.getBoardEntity().getId());
//        likesDTO.setCommentId(likesEntity.getCommentEntity().getId());
        return likesDTO;
    }
    public static LikesDTO toLikeList(LikesEntity likesEntity){
        LikesDTO likesDTO = new LikesDTO();
        likesDTO.setId(likesEntity.getId());
        likesDTO.setMemberName(likesEntity.getMemberName());
        likesDTO.setBoardId(likesEntity.getBoardEntity().getId());
//        likesDTO.setBoardId(likesEntity.getCommentEntity().getId());
        return likesDTO;
    }

}
