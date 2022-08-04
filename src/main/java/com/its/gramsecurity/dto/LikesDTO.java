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
    private Integer likes;

    public static LikesDTO toLikeSave(LikesEntity likesEntity){
        LikesDTO likesDTO = new LikesDTO();
        likesDTO.setId(likesEntity.getId());
        likesDTO.setMemberName(likesEntity.getMemberName());
        likesDTO.setBoardId(likesEntity.getBoardId());
        likesDTO.setLikes(likesEntity.getLikes());
        return likesDTO;
    }
    public static LikesDTO toLikeList(LikesEntity likesEntity){
        LikesDTO likesDTO = new LikesDTO();
        likesDTO.setId(likesEntity.getId());
        likesDTO.setMemberId(likesEntity.getMemberId());
        likesDTO.setBoardId(likesEntity.getBoardId());
        likesDTO.setLikes(likesEntity.getLikes());
        return likesDTO;
    }

}
