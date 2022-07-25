package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardContents;
    private String boardLocation;
    private LocalDateTime boardCreatedTime;
    private String boardTag;
    private List<BoardFileDTO> boardFileList;

    public static BoardDTO toDTO(BoardEntity boardFile) {
        BoardDTO boardFileDTO = new BoardDTO();
        boardFileDTO.setId(boardFile.getId());
        boardFileDTO.setBoardWriter(boardFile.getBoardWriter());
        boardFileDTO.setBoardContents(boardFile.getBoardContents());
        boardFileDTO.setBoardLocation(boardFile.getBoardLocation());
        boardFileDTO.setBoardTag(boardFile.getBoardTag());
        return boardFileDTO;
    }
}
