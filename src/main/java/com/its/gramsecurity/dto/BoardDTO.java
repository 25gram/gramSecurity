package com.its.gramsecurity.dto;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.entity.BoardEntity;
import com.its.gramsecurity.entity.BoardFileEntity;
import com.its.gramsecurity.entity.LikesEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String loginId;
    private String boardContents;
    private String boardLocation;
    private LocalDateTime boardCreatedTime;
    private String boardTag;
    private List<BoardFileDTO> boardFileList;
    private String memberProfileName;
    private Integer likes;

    public static BoardDTO toDTO(BoardEntity boardFile, PrincipalDetails principalDetails) {
        BoardDTO boardFileDTO = new BoardDTO();
        boardFileDTO.setLoginId(principalDetails.getMemberDTO().getLoginId());
        boardFileDTO.setId(boardFile.getId());
        boardFileDTO.setBoardWriter(boardFile.getBoardWriter());
        boardFileDTO.setBoardContents(boardFile.getBoardContents());
        boardFileDTO.setBoardLocation(boardFile.getBoardLocation());
        boardFileDTO.setBoardTag(boardFile.getBoardTag());
        return boardFileDTO;
    }
    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setLoginId(boardEntity.getLoginId());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardLocation(boardEntity.getBoardLocation());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardTag(boardEntity.getBoardTag());
        boardDTO.setMemberProfileName(boardEntity.getMemberProfileName());
        boardDTO.setLikes(boardEntity.getLikes());
        return boardDTO;
    }

}
