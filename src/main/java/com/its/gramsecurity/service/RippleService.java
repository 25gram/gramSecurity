package com.its.gramsecurity.service;

import com.its.gramsecurity.dto.CommentDTO;
import com.its.gramsecurity.dto.RippleDTO;
import com.its.gramsecurity.entity.BoardEntity;
import com.its.gramsecurity.entity.CommentEntity;
import com.its.gramsecurity.entity.MemberEntity;
import com.its.gramsecurity.entity.RippleEntity;
import com.its.gramsecurity.repository.BoardRepository;
import com.its.gramsecurity.repository.CommentRepository;
import com.its.gramsecurity.repository.MemberRepository;
import com.its.gramsecurity.repository.RippleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RippleService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    private final RippleRepository rippleRepository;

    public void save(RippleDTO rippleDTO) {
//        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByLoginId(loginId);
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(rippleDTO.getCommentId());
        if(optionalCommentEntity.isPresent()){
//            MemberEntity memberEntity = optionalMemberEntity.get();
            CommentEntity commentEntity = optionalCommentEntity.get();
            RippleEntity rippleEntity = RippleEntity.toSaveEntity(rippleDTO);
            rippleRepository.save(rippleEntity);
        }

    }

    public List<RippleDTO> findAll() {
        List<RippleEntity> rippleList = rippleRepository.findAll();
        System.out.println(rippleList);
        List<RippleDTO> ripple = new ArrayList<>();
        for(RippleEntity rippleEntity : rippleList){
            ripple.add(RippleDTO.toFindDTO(rippleEntity));
        }return ripple;
    }

}
