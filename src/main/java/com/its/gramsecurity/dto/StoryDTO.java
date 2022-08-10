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
    private String storyImgTag;
    private String storyTextTag;
    private String storyTodayTag;
    private String storyLinkTag;
    private String storyQuestionTag;
    private String storySurveyTag;
    private String storyQuizTag;
    private String storyHashTag;
    private String storyReferenceTag;
    private String storyLocationTag;
    private int storyVisitStatus;

    public static StoryDTO toStoryDTO (StoryEntity storyEntity) {
        StoryDTO storyDTO = new StoryDTO();
        storyDTO.setId(storyEntity.getId());
        storyDTO.setMemberName(storyEntity.getMemberName());
        storyDTO.setStoryFileName(storyEntity.getStoryFileName());
        storyDTO.setStoryCreatedTime(storyEntity.getStoryCreatedTime());
        storyDTO.setStoryImgTag(storyEntity.getStoryImgTag());
        storyDTO.setStoryTextTag(storyEntity.getStoryTextTag());
        storyDTO.setStoryTodayTag(storyEntity.getStoryTodayTag());
        storyDTO.setStoryLinkTag(storyEntity.getStoryLinkTag());
        storyDTO.setStoryQuestionTag(storyEntity.getStoryQuestionTag());
        storyDTO.setStorySurveyTag(storyEntity.getStorySurveyTag());
        storyDTO.setStoryQuizTag(storyEntity.getStoryQuizTag());
        storyDTO.setStoryHashTag(storyEntity.getStoryHashTag());
        storyDTO.setStoryReferenceTag(storyEntity.getStoryReferenceTag());
        storyDTO.setStoryLocationTag(storyEntity.getStoryLocationTag());
        storyDTO.setStoryVisitStatus(storyEntity.getStoryVisitStatus());
        return storyDTO;
    }
}
