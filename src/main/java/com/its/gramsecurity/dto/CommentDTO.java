package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.CommentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private Long boardId;
    private String commentWriter;
    private String commentContents;
    private LocalDateTime commentCreatedTime;



    public CommentDTO(Long boardId, String commentWriter, String commentContents, LocalDateTime commentCreatedTime) {
        this.boardId = boardId;
        this.commentWriter = commentWriter;
        this.commentContents = commentContents;
        this.commentCreatedTime = commentCreatedTime;
    }

    public static CommentDTO toSaveDTO(CommentEntity commentEntity){
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        return commentDTO;

    }

}
