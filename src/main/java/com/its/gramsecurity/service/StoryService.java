package com.its.gramsecurity.service;

import com.its.gramsecurity.dto.StoryDTO;
import com.its.gramsecurity.entity.StoryEntity;
import com.its.gramsecurity.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoryService {
    private final StoryRepository storyRepository;
    public List<StoryDTO> findByMemberName(String memberName) {
        List<StoryDTO> storyDTOList = new ArrayList<>();
        List<StoryEntity> storyEntityList = storyRepository.findByMemberName(memberName);
        for(StoryEntity story: storyEntityList) {
            storyDTOList.add(StoryDTO.toStoryDTO(story));
        }
        return storyDTOList;
    }

    public List<StoryDTO> findByLocation(String storyLocation) {
        List<StoryEntity> storyEntityList = storyRepository.findByStoryLocation(storyLocation);
        List<StoryDTO> storyDTOList = new ArrayList<>();
        for(StoryEntity story: storyEntityList){
            storyDTOList.add(StoryDTO.toStoryDTO(story));
        } return storyDTOList;
    }
}
