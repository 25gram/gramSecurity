package com.its.gramsecurity.entity;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.BoardDTO;
import com.its.gramsecurity.dto.LikesDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board_entity")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(length = 500)
    private String boardWriter;

    @Column(length = 1000)
    private String boardContents;

    @Column(length = 50)
    private String boardLocation;

    @Column(length = 200)
    private String boardTag;


    @Column (length = 500)
    private String memberProfileName;

    @Column
    private Integer likes;


    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL,orphanRemoval = true ,fetch=FetchType.LAZY)
    List<BoardFileEntity> fileEntityList= new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    List<CommentEntity>commentEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    List<LikesEntity>likesEntityList=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;
    public static BoardEntity toSaveEntity(BoardDTO boardDTO, PrincipalDetails principalDetails) {
        BoardEntity board = new BoardEntity();
        board.setBoardWriter(principalDetails.getName());
        board.setMemberProfileName(principalDetails.getMemberDTO().getMemberProfileName());
        board.setBoardContents(boardDTO.getBoardContents());
        board.setBoardLocation(boardDTO.getBoardLocation());
        board.setBoardTag(boardDTO.getBoardTag());
        return board;
    }

    public static BoardEntity toUpdateSave(BoardDTO boardDTO) {
        BoardEntity board = new BoardEntity();
        board.setId(boardDTO.getId());
        board.setBoardWriter(boardDTO.getBoardWriter());
        board.setBoardContents(boardDTO.getBoardContents());
        board.setBoardLocation(boardDTO.getBoardLocation());
        board.setBoardTag(boardDTO.getBoardTag());
        board.setLikes(1);
        return board;
    }
    public static BoardEntity toDelete(BoardDTO boardDTO) {
        BoardEntity board = new BoardEntity();
        board.setId(boardDTO.getId());
        board.setBoardWriter(boardDTO.getBoardWriter());
        board.setBoardContents(boardDTO.getBoardContents());
        board.setBoardLocation(boardDTO.getBoardLocation());
        board.setBoardTag(boardDTO.getBoardTag());
        board.setLikes(null);
        return board;
    }
}
