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
    public void save(FollowDTO followDTO, String myId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByLoginId(myId);
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            FollowEntity followEntity = FollowEntity.toSaveEntity(followDTO, memberEntity, myId);
            followRepository.save(followEntity);
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

    public List<FollowDTO> findAllByMyId(String myId) {
        List<FollowEntity> followEntityList = followRepository.findAllByMyId(myId);
        List<FollowDTO> followList = new ArrayList<>();
        for (FollowEntity follow : followEntityList) {
            followList.add(FollowDTO.toDTO(follow));
        }
        return followList;
    }

    public List<FollowDTO> findAllByYourId(String myId) {
        List<FollowEntity> followingEntityList = followRepository.findAllByYourId(myId);
        List<FollowDTO> followingList = new ArrayList<>();
        for (FollowEntity follow : followingEntityList) {
            followingList.add(FollowDTO.toDTO(follow));
        }
        return followingList;
    }

    public String UnFollow(String yourId, String myId) {
        System.out.println("FollowService.UnFollow");
        System.out.println("yourId = " + yourId + ", myId = " + myId);
        followRepository.deleteByYourIdAndMyId(yourId, myId);
        return "confirm";
    }


    public String findByMyIdAndYourId(String myId, String yourId) {
        Optional<FollowEntity> optionalFollowEntity = followRepository.findByMyIdAndYourId(myId,yourId);
        System.out.println("FollowService.findByMyIdAndYourId");
        System.out.println("myId = " + myId + ", yourId = " + yourId);
        System.out.println("optionalFollowEntity = " + optionalFollowEntity);
        if (optionalFollowEntity.isPresent()) {
            return "찾았다";
        } else {
            return "못찾았다";
        }

    }
}
