package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.BoardFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardFileDTO {
    private Long id;
    private Long boardId;
    private String boardImgName;
    private String boardVideoName;
    private MultipartFile boardFile;
    private String boardFilter;
    public static BoardFileDTO toDTO(BoardFileEntity file) {
        BoardFileDTO fileDTO = new BoardFileDTO();
        fileDTO.setId(file.getId());
        fileDTO.setBoardImgName(file.getBoardImgName());
        fileDTO.setBoardVideoName(file.getBoardVideoName());
        fileDTO.setBoardFilter(file.getBoardFilter());
        return fileDTO;
    }
    public static BoardFileDTO toListDTO(BoardFileEntity file) {
        BoardFileDTO fileDTO = new BoardFileDTO();
        fileDTO.setId(file.getId());
        fileDTO.setBoardId(file.getBoardId());
        fileDTO.setBoardImgName(file.getBoardImgName());
        fileDTO.setBoardVideoName(file.getBoardVideoName());
        fileDTO.setBoardFilter(file.getBoardFilter());
        return fileDTO;
    }
}
