package com.its.gramsecurity.service;

import com.its.gramsecurity.dto.CommentDTO;
import com.its.gramsecurity.entity.BoardEntity;
import com.its.gramsecurity.entity.BoardFileEntity;
import com.its.gramsecurity.entity.CommentEntity;
import com.its.gramsecurity.entity.MemberEntity;
import com.its.gramsecurity.repository.BoardRepository;
import com.its.gramsecurity.repository.CommentRepository;
import com.its.gramsecurity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

//    public void save(CommentDTO commentDTO) {
//        Optional<MemberEntity>optionalMemberEntity=memberRepository.findByMemberId(commentDTO.getCommentWriter());
//        Optional<BoardEntity>optionalBoardEntity=boardRepository.findByBoardId(commentDTO.getBoardId());
//        if(optionalMemberEntity.isPresent() && optionalMemberEntity.isPresent()){
//            MemberEntity memberEntity=optionalMemberEntity.get();
//            BoardEntity boardEntity=optionalBoardEntity.get();
//            CommentEntity commentEntity=CommentEntity.toSaveEntity(commentDTO,memberEntity,boardEntity);
//            commentRepository.save(commentEntity);
//        }
//
//    }
//
//    public List<Optional<CommentEntity>> findAll(Long boardId) {
//        List<Optional<CommentEntity>>commentEntityList=commentRepository.findByBoardId(boardId);
//        List<Optional<CommentEntity>>commentDTOList=new ArrayList<>();
//        for(Optional<CommentEntity> commentEntity : commentEntityList){
//            commentDTOList.add(commentEntity);
//        }return commentDTOList;
//    }
}
