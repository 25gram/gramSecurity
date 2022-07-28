package com.its.gramsecurity.service;

import com.its.gramsecurity.dto.FollowDTO;
import com.its.gramsecurity.entity.FollowEntity;
import com.its.gramsecurity.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;

    public void save(FollowDTO followDTO) {
        FollowEntity followEntity = FollowEntity.toSaveEntity(followDTO);
        followRepository.save(followEntity);
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

    public List<Optional<FollowEntity>> findAllByMyId(Long myId) {
        List<Optional<FollowEntity>> followEntityList = followRepository.findAllByMyId(myId);
        List<Optional<FollowEntity>> followList = new ArrayList<>();
        for (Optional<FollowEntity> follow : followEntityList) {
            followList.add(follow);
        }
        return followList;
    }


    //    public void UnFollow(Long id) {
//        followRepository.deleteById(id);
//    }
//
//
}
