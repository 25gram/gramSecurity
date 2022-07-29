package com.its.gramsecurity.service;

import com.its.gramsecurity.dto.FollowDTO;
import com.its.gramsecurity.entity.FollowEntity;
import com.its.gramsecurity.entity.MemberEntity;
import com.its.gramsecurity.repository.FollowRepository;
import com.its.gramsecurity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    //테스트코드 사용중
    public Long save(FollowDTO followDTO,String myId) {
        Optional<MemberEntity>optionalMemberEntity=memberRepository.findByMemberId(followDTO.getMyId());
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity=optionalMemberEntity.get();
            FollowEntity followEntity=FollowEntity.toSaveEntity(followDTO,memberEntity,myId);
            Long savedId= followRepository.save(followEntity).getId();
            return savedId;
        }else{
            return null;
        }
    }

    public List<FollowDTO> findAll() {
        List<FollowEntity> followEntityList = followRepository.findAll();
        List<FollowDTO> followDTOList = new ArrayList<>();
        for (FollowEntity follow : followEntityList) {
            FollowDTO followDTO = FollowDTO.toDTO(follow);
            followDTOList.add(followDTO);
        }
        return followDTOList;
    }

    public List<Optional<FollowEntity>> findAllByMyId(String myId) {
        List<Optional<FollowEntity>> followEntityList = followRepository.findAllByMyId(myId);
        List<Optional<FollowEntity>> followList = new ArrayList<>();
        for (Optional<FollowEntity> follow : followEntityList) {
            followList.add(follow);
        }
        return followList;
    }

    public List<Optional<FollowEntity>> findAllByYourId(String yourId) {
        List<Optional<FollowEntity>>followingEntityList=followRepository.findAllByYourId(yourId);
        List<Optional<FollowEntity>>followingList=new ArrayList<>();
        for(Optional<FollowEntity> follow:followingEntityList){
            followingList.add(follow);
        }return followingList;
    }

    public void UnFollow(Long id) {
        followRepository.deleteById(id);
    }


}
