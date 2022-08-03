package com.its.gramsecurity.entity;

import com.its.gramsecurity.dto.CommentDTO;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment_entity")
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String commentWriter;

    @Column
    private String commentContents;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;


    public static CommentEntity toSaveEntity(CommentDTO commentDTO, MemberEntity memberEntity,BoardEntity boardEntity){
        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setId(commentDTO.getId());
        commentEntity.setCommentWriter(memberEntity.getMemberId());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setMemberEntity(memberEntity);
        commentEntity.setBoardEntity(boardEntity);
        return commentEntity;
    }


}
