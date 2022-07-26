package com.its.gramsecurity.entity;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.CommentDTO;
import com.its.gramsecurity.dto.RippleDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ripple_entity")
public class RippleEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ripple_id")
    private Long id;

    @Column
    private String rippleContents;

    @Column
    private String rippleWriter;

    @Column
    private Long commentId;

    @Column
    private String tagName;

    @Column
    private String tagLogin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private CommentEntity commentEntity;

    @OneToMany(mappedBy = "rippleEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    List<LikesEntity> likesEntityList = new ArrayList<>();

    public static RippleEntity toSaveEntity(RippleDTO rippleDTO,CommentEntity commentEntity,MemberEntity memberEntity, MemberEntity login){
        RippleEntity rippleEntity = new RippleEntity();
        rippleEntity.setId(rippleDTO.getId());
        rippleEntity.setRippleContents(rippleDTO.getRippleContents());
        rippleEntity.setCommentId(rippleDTO.getCommentId());
        rippleEntity.setRippleWriter(memberEntity.getMemberName());
        rippleEntity.setTagName(login.getMemberName());
        rippleEntity.setTagLogin(login.getLoginId());
        rippleEntity.setMemberEntity(memberEntity);
        rippleEntity.setCommentEntity(commentEntity);
        return rippleEntity;
    }
}
