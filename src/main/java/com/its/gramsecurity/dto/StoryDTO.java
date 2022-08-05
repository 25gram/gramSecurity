package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.StoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoryDTO {
    private Long id;
    private String memberId;
    private String storyFileName;
    private LocalDateTime storyCreatedTime;
    private String storyHashtag;
    private String storyLocation;
    private int storyVisitStatus;


    public static StoryDTO toStoryDTO (StoryEntity storyEntity) {
        StoryDTO storyDTO = new StoryDTO();
        storyDTO.setId(storyEntity.getId());
        storyDTO.setMemberId(storyEntity.getMemberId());
        storyDTO.setStoryFileName(storyEntity.getStoryFileName());
        storyDTO.setStoryCreatedTime(storyEntity.getStoryCreatedTime());
        storyDTO.setStoryHashtag(storyEntity.getStoryHashtag());
        storyDTO.setStoryLocation(storyEntity.getStoryLocation());
        storyDTO.setStoryVisitStatus(storyEntity.getStoryVisitStatus());
        return storyDTO;
    }
}
