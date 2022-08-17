package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.StoryViewEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryViewDTO {
    private Long id;
    private String loginId;
    private Long storyId;
    private Integer viewStatus;

    public static StoryViewDTO toStoryViewDTOSave(StoryViewEntity storyViewEntity){
        StoryViewDTO storyViewDTO = new StoryViewDTO();
        storyViewDTO.setId(storyViewEntity.getId());
        storyViewDTO.setLoginId(storyViewEntity.getStoryEntity().getLoginId());
        storyViewDTO.setStoryId(storyViewEntity.getStoryEntity().getId());
        return storyViewDTO;
    }
    public static StoryViewDTO toStoryViewList(StoryViewEntity storyViewEntity){
        StoryViewDTO storyViewDTO = new StoryViewDTO();
        storyViewDTO.setId(storyViewEntity.getId());
        storyViewDTO.setLoginId(storyViewEntity.getStoryEntity().getLoginId());
        storyViewDTO.setStoryId(storyViewEntity.getStoryEntity().getId());
        return storyViewDTO;
    }

}
