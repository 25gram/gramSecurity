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
    private Integer likes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    public static LikesEntity toLikesEntity(LikesDTO likesDTO, PrincipalDetails principalDetails) {
        LikesEntity likes = new LikesEntity();
        likes.setMemberName(principalDetails.getName());
        likes.setBoardId(likesDTO.getBoardId());
        likes.setLikes(likesDTO.getLikes());
        return likes;
    }

}
