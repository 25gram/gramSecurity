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
        FollowEntity followEntity=FollowEntity.toSaveEntity(followDTO);
        followRepository.save(followEntity);
    }

//    public void UnFollow(Long id) {
//        followRepository.deleteById(id);
//    }
//
//
//    public List<FollowDTO> findById(Long id) {
//        List<FollowEntity>followEntityList=followRepository.followList(id);
//        List<FollowDTO>followDTOList=new ArrayList<>();
//        for(FollowEntity followEntity:followEntityList){
//            FollowDTO followDTO=FollowDTO.toDTO(followEntity);
//            followDTOList.add(followDTO);
//        }return followDTOList;
//    }
}
