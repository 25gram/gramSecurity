package com.its.gramsecurity.service;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.LikesDTO;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.*;
import com.its.gramsecurity.repository.*;
import com.its.gramsecurity.dto.BoardDTO;
import com.its.gramsecurity.dto.BoardFileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardFileRepository boardFileRepository;
    private final BoardRepository boardRepository;
    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;
    public BoardDTO fileSave(BoardDTO boardDTO, PrincipalDetails principalDetails) {
        Long id = boardRepository.save(BoardEntity.toSaveEntity(boardDTO,principalDetails)).getId();
        return BoardDTO.toDTO(boardRepository.findById(id).get(),principalDetails);
    }
    public BoardFileDTO save(BoardFileDTO fileDTO,String list) throws IOException {
        MultipartFile boardFile = fileDTO.getBoardFile();
        String fileName = boardFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        System.out.println(fileDTO.getId());
        final String[] a = {"jpeg","jpg","png"};
        int len = a.length;
        final String[] b = {"mp4"};
        int len1 = b.length;
        if (!boardFile.isEmpty()){
            fileName = System.currentTimeMillis()+ "_" + fileName;
            String savePath = "C:\\springboot_img\\" + fileName;
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
        List<BoardEntity> boardEntity = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
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
    public void likesFindAll(String loginId) {
        List<BoardEntity> boardEntity = boardRepository.findAll();
        List<LikesEntity> likesEntityList = likesRepository.findByMemberName(loginId);
        for (BoardEntity boardList : boardEntity) {
            boardRepository.likesDelete(boardList.getId());
        }
        for (int i = 0; i < likesEntityList.size(); i++) {
            for (BoardEntity board : boardEntity) {
                if (board.getId() == likesEntityList.get(i).getBoardId()) {
                    boardRepository.likes(likesEntityList.get(i).getBoardId());
                }
            }
        }
    }
    public String likes(LikesDTO likesDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String loginId = principalDetails.getMemberDTO().getLoginId();
        Optional<MemberEntity> memberEntity = memberRepository.findByLoginId(loginId);
        Optional<BoardEntity> boardEntity = boardRepository.findById(likesDTO.getBoardId());
        if (boardEntity.isPresent()  && memberEntity.isPresent()){
            MemberEntity member = memberEntity.get();
            BoardEntity board = boardEntity.get();
            if (board.getLikes() == null) {
                LikesEntity likesEntity2 = likesRepository.save(LikesEntity.toLikesEntity(likesDTO, member, board));
                LikesDTO.toLikeSave(likesEntity2);
                Long id = likesDTO.getBoardId();
                boardRepository.likes(id);
                return "ok";
            }else if (board.getLikes() == 1) {
                Optional<LikesEntity> likesEntity = likesRepository.findByMemberNameAndBoardEntity(likesDTO.getMemberName(), board);
                if (likesEntity.isPresent()){
                    LikesEntity likes = likesEntity.get();
                    if (likes.getMemberName().equals(likesDTO.getMemberName()) && likes.getBoardEntity().getId().equals(likesDTO.getBoardId())){
                        likesRepository.delete(likes);
                    }
                }
                Long id = likesDTO.getBoardId();
                boardRepository.likesDelete(id);
            }
        }
        return "no";
    }
    public List<BoardFileDTO> detail(Long id) {
        List<BoardFileEntity> a = boardFileRepository.findByBoardId(id);
        List<BoardFileDTO> list = new ArrayList<>();
        for (BoardFileEntity b : a) {
            list.add(BoardFileDTO.toListDTO(b));
        }
        return list;
    }

     void updateProfile(MemberDTO memberDTO,String fileName){
//        BoardEntity blist=boardRepository.findByLoginId(memberDTO.getLoginId());
        List<BoardEntity> blist=boardRepository.findByLoginId(memberDTO.getLoginId());
        for (int i =0;i< blist.size();i++){
            blist.get(i).setMemberProfileName(fileName);
            boardRepository.save(blist.get(i));
        }

    }
    public List<LikesDTO> count() {
        List<LikesEntity> likesEntity = likesRepository.findAll();
        List<LikesDTO> list = new ArrayList<>();
        for (LikesEntity likes : likesEntity) {
            list.add(LikesDTO.toLikeList(likes));
        }
        return list;
    }
    public List<BoardDTO> boardFind () {
        List<BoardEntity> boardEntity = boardRepository.findAll();
        System.out.println(boardEntity);
        List<BoardDTO> board = new ArrayList<>();
        for (BoardEntity boardList : boardEntity) {
            board.add(BoardDTO.toBoardDTO(boardList));
        }
        return board;
    }

    public List<BoardFileDTO> detailFind(String loginId) {
        List<BoardEntity> boardEntity = boardRepository.findAll();
        List<BoardFileEntity> boardFileEntity = boardFileRepository.findAll();
        List<BoardFileDTO> boardFileEntityList = new ArrayList<>();
        for (int i = 0; i < boardEntity.size(); i++) {
            if (boardEntity.get(i).getBoardWriter().equals(loginId)) {
                for (int j = 0; j < boardFileEntity.size(); j++) {
                    if (boardEntity.get(i).getId() == boardFileEntity.get(j).getBoardId()){
                        boardFileEntityList.add(BoardFileDTO.toListDTO(boardFileEntity.get(j)));
                    }
                }
            }
        }
        return boardFileEntityList;
    }
}
