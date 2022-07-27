package com.its.gramsecurity.entity;

import com.its.gramsecurity.dto.BoardFileDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "boardFile_table")
public class BoardFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column
    private Long boardId;

    @Column(length = 50)
    private String boardImgName;

    @Column(length = 50)
    private String boardVideoName;

    @Column
    private String boardFilter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;
    public static BoardFileEntity toSaveEntity(BoardFileDTO fileDTO, BoardEntity boardFile) {
        BoardFileEntity file = new BoardFileEntity();
        file.setBoardImgName(fileDTO.getBoardImgName());
        file.setBoardVideoName(fileDTO.getBoardVideoName());
        file.setBoardFilter(fileDTO.getBoardFilter());
        System.out.println("aaa" + fileDTO.getBoardFilter());
        file.setBoardEntity(boardFile);
        return file;
    }
}
