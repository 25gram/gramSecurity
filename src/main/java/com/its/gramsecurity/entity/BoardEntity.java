package com.its.gramsecurity.entity;

import com.its.gramsecurity.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(length = 20)
    private String boardWriter;

    @Column(length = 100)
    private String boardContents;

    @Column(length = 50)
    private String boardLocation;

    @Column(length = 200)
    private String boardTag;


    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL,orphanRemoval = true ,fetch=FetchType.LAZY)
    List<BoardFileEntity> fileEntityList= new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    List<CommentEntity>commentEntityList=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;
    public static BoardEntity toSaveEntity(BoardDTO boardDTO,String memberId) {
        BoardEntity board = new BoardEntity();
        board.setBoardWriter(memberId);
        board.setBoardContents(boardDTO.getBoardContents());
        board.setBoardLocation(boardDTO.getBoardLocation());
        board.setBoardTag(boardDTO.getBoardTag());
        return board;
    }
}
