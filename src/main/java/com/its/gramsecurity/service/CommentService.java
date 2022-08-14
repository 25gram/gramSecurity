package com.its.gramsecurity.service;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.CommentDTO;
import com.its.gramsecurity.dto.LikesDTO;
import com.its.gramsecurity.dto.RippleDTO;
import com.its.gramsecurity.entity.*;
import com.its.gramsecurity.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final LikesRepository likesRepository;

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
        List<CommentEntity> commentEntityList = commentRepository.findAll();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(CommentEntity commentEntity : commentEntityList){
            commentDTOList.add(CommentDTO.toSaveDTO(commentEntity));
        }
        return commentDTOList;
    }


    public String likes(LikesDTO likesDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String loginId = principalDetails.getMemberDTO().getLoginId();
        Optional<MemberEntity> memberEntity = memberRepository.findByLoginId(loginId);
        Optional<CommentEntity> commentEntity = commentRepository.findById(likesDTO.getCommentId());
        if (commentEntity.isPresent()  && memberEntity.isPresent()){
            MemberEntity member = memberEntity.get();
            CommentEntity comment = commentEntity.get();
            if (comment.getLikes() == null) {
                LikesEntity likesEntity2 = likesRepository.save(LikesEntity.toLikesCommentEntity(likesDTO, member, comment));
                LikesDTO.toCommentLike(likesEntity2);
                Long id = likesDTO.getCommentId();
                commentRepository.likes(id);
                return "ok";
            }else if (comment.getLikes() == 1) {
                Optional<LikesEntity> likesEntity = likesRepository.findByMemberNameAndCommentEntity(likesDTO.getMemberName(), comment);
                if (likesEntity.isPresent()){
                    LikesEntity likes = likesEntity.get();
                    if (likes.getMemberName().equals(likesDTO.getMemberName()) && likes.getCommentEntity().getId().equals(likesDTO.getCommentId())){
                        likesRepository.delete(likes);
                    }
                }
                Long id = likesDTO.getCommentId();
                commentRepository.likesDelete(id);
            }
        }
        return "no";
    }

    public List<LikesDTO> count() {
        List<LikesEntity> likesEntity = likesRepository.findAll();
        List<LikesDTO> list = new ArrayList<>();
        for (LikesEntity likes : likesEntity) {
            if (likes.getCommentId() != null) {
                list.add(LikesDTO.toCommentList(likes));
            }
        }
        return list;
    }
    public void likesFindAll(String loginId) {
        List<CommentEntity> commentEntity = commentRepository.findAll();
        List<LikesEntity> likesEntityList = likesRepository.findByMemberName(loginId);
        for (CommentEntity commentList : commentEntity) {
            commentRepository.likesDelete(commentList.getId());
        }
        for (int i = 0; i < likesEntityList.size(); i++) {
            for (CommentEntity comment : commentEntity) {
                if (comment.getId() == likesEntityList.get(i).getCommentId()) {
                    commentRepository.likes(likesEntityList.get(i).getCommentId());
                }
            }
        }

    }
}
