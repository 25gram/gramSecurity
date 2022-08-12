package com.its.gramsecurity.entity;

import com.its.gramsecurity.dto.CommentDTO;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "commentEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    List<LikesEntity> likesEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "commentEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    List<RippleEntity> rippleEntityList = new ArrayList<>();


    public static CommentEntity toSaveEntity(CommentDTO commentDTO, MemberEntity memberEntity,BoardEntity boardEntity){
        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setId(commentDTO.getId());
        commentEntity.setCommentWriter(memberEntity.getMemberName());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setMemberEntity(memberEntity);
        commentEntity.setBoardEntity(boardEntity);
        return commentEntity;
    }
}
