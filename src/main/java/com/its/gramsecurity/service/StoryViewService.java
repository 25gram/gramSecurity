package com.its.gramsecurity.service;

import com.its.gramsecurity.dto.StoryViewDTO;
import com.its.gramsecurity.entity.StoryEntity;
import com.its.gramsecurity.entity.StoryViewEntity;
import com.its.gramsecurity.repository.StoryRepository;
import com.its.gramsecurity.repository.StoryViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoryViewService {
    private final StoryViewRepository storyViewRepository;
    private final StoryRepository storyRepository;
    public void storyViewSave(String loginId) {
        StoryViewDTO storyViewDTO = new StoryViewDTO();
        StoryViewEntity storyViewEntity = new StoryViewEntity();
        List<StoryEntity> storyEntityList = storyRepository.findByLoginId(loginId);
        for(StoryEntity story: storyEntityList){
            if(story!=null){
                storyViewDTO.setStoryId(story.getId());
                storyViewDTO.setLoginId(story.getLoginId());
                storyViewEntity = StoryViewEntity.toStoryViewEntity(storyViewDTO, story);
            }
        }
        storyViewRepository.save(storyViewEntity);
    }
    public boolean findByStoryIdAndLoginId(Long id, String loginId){
        boolean result = storyViewRepository.findByStoryIdANDLoginId(id, loginId);
        return result;
    }
}
