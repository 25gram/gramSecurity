package com.its.gramsecurity.entity;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.BoardDTO;
import com.its.gramsecurity.dto.LikesDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.security.Principal;

@Entity
@Getter
@Setter
@Table(name = "likes_entity")
public class LikesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String memberName;

    @Column
    private Long boardId;

    @Column
    private Long commentId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private CommentEntity commentEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ripple_id")
    private RippleEntity rippleEntity;

    public static LikesEntity toLikesEntity(LikesDTO likesDTO, MemberEntity memberEntity, BoardEntity boardEntity) {
        LikesEntity likes = new LikesEntity();
        likes.setMemberName(memberEntity.getMemberName());
        likes.setBoardId(boardEntity.getId());
        likes.setBoardEntity(boardEntity);
        return likes;
    }
    public static LikesEntity toLikesCommentEntity(LikesDTO likesDTO, MemberEntity memberEntity, CommentEntity commentEntity) {
        LikesEntity likes = new LikesEntity();
        likes.setMemberName(memberEntity.getMemberName());
        likes.setCommentEntity(commentEntity);
        likes.setCommentId(commentEntity.getId());
        return likes;
    }

}
