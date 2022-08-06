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
    private String memberName;
    private String storyFileName;
    private LocalDateTime storyCreatedTime;
    private String storyHashtag;
    private String storyLocation;
    private int storyVisitStatus;

    public StoryDTO(String memberName, String storyFileName, LocalDateTime storyCreatedTime, int storyVisitStatus) {
        this.memberName = memberName;
        this.storyFileName = storyFileName;
        this.storyCreatedTime = storyCreatedTime;
        this.storyVisitStatus = storyVisitStatus;
    }
    public static StoryDTO toStoryDTO (StoryEntity storyEntity) {
        StoryDTO storyDTO = new StoryDTO();
        storyDTO.setId(storyEntity.getId());
        storyDTO.setMemberName(storyEntity.getMemberName());
        storyDTO.setStoryFileName(storyEntity.getStoryFileName());
        storyDTO.setStoryCreatedTime(storyEntity.getStoryCreatedTime());
        storyDTO.setStoryHashtag(storyEntity.getStoryHashtag());
        storyDTO.setStoryLocation(storyEntity.getStoryLocation());
        storyDTO.setStoryVisitStatus(storyEntity.getStoryVisitStatus());
        return storyDTO;
    }
}
