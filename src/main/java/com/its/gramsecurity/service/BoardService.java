package com.its.gramsecurity.service;

import com.its.gramsecurity.repository.BoardFileRepository;
import com.its.gramsecurity.repository.BoardRepository;
import com.its.gramsecurity.dto.BoardDTO;
import com.its.gramsecurity.dto.BoardFileDTO;
import com.its.gramsecurity.entity.BoardEntity;
import com.its.gramsecurity.entity.BoardFileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardFileRepository boardFileRepository;
    private final BoardRepository boardRepository;
    public BoardDTO fileSave(BoardDTO boardDTO,String memberId) {
        Long id = boardRepository.save(BoardEntity.toSaveEntity(boardDTO,memberId)).getId();
        return BoardDTO.toDTO(boardRepository.findById(id).get());
    }
    public BoardFileDTO save(BoardFileDTO fileDTO,String list) throws IOException {
        MultipartFile boardFile = fileDTO.getBoardFile();
        String fileName = boardFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        System.out.println(fileDTO.getId());
        final String[] a = {"jpg","png"};
        int len = a.length;
        final String[] b = {"mp4"};
        int len1 = b.length;
        if (!boardFile.isEmpty()){
            fileName = System.currentTimeMillis()+ "_" + fileName;
            String savePath = "D:\\springboot_img\\" + fileName;
            boardFile.transferTo(new File(savePath));
            for (int i = 0; i < len; i++){
                if(ext.equalsIgnoreCase(a[i])){
                    fileDTO.setBoardImgName(fileName);
                }
            }

            for (int i = 0; i < len1; i++){
                if(ext.equalsIgnoreCase(b[i])){
                    fileDTO.setBoardVideoName(fileName);
                }
            }
        }
        fileDTO.setBoardFilter(list);
        BoardEntity boardEntity = boardRepository.findById(fileDTO.getBoardId()).get();
        Long id = boardFileRepository.save(BoardFileEntity.toSaveEntity(fileDTO, boardEntity)).getId();
        return BoardFileDTO.toDTO(boardFileRepository.findById(id).get());
    }
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntity = boardRepository.findAll();
        List<BoardDTO> board = new ArrayList<>();
        for (BoardEntity boardList : boardEntity) {
            board.add(BoardDTO.toBoardDTO(boardList));
        }
        return board;
    }
    public List<BoardFileDTO> fileFindAll() {
        List<BoardFileEntity> boardEntity = boardFileRepository.findAll();
        List<BoardFileDTO> board = new ArrayList<>();
        for (BoardFileEntity boardList : boardEntity) {
            board.add(BoardFileDTO.toListDTO(boardList));
        }
        return board;
    }


}
