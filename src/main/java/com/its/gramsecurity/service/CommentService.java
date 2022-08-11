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

    public void save(CommentDTO commentDTO,String loginId) {
        Optional<MemberEntity>optionalMemberEntity=memberRepository.findByLoginId(loginId);
        Optional<BoardEntity>optionalBoardEntity=boardRepository.findById(commentDTO.getBoardId());
        if(optionalMemberEntity.isPresent() && optionalBoardEntity.isPresent()){
            MemberEntity memberEntity=optionalMemberEntity.get();
            BoardEntity boardEntity=optionalBoardEntity.get();
            CommentEntity commentEntity=CommentEntity.toSaveEntity(commentDTO,memberEntity,boardEntity);
            commentRepository.save(commentEntity);
        }

    }

    public List<CommentDTO> findAll() {
        List<CommentEntity>commentEntityList=commentRepository.findAll();
        System.out.println(commentEntityList);
        List<CommentDTO>commentDTOList=new ArrayList<>();
        for(CommentEntity commentEntity : commentEntityList){
            commentDTOList.add(CommentDTO.toSaveDTO(commentEntity));
        }return commentDTOList;
    }

}
