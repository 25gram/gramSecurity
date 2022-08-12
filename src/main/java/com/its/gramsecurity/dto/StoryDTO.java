package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.StoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoryDTO {
    private Long id;
    private String loginId;
    private String memberName;
    private String storyFileName;
    private MultipartFile storyFile;
    private LocalDateTime storyCreatedTime;
    private String storyImgTag;
    private MultipartFile storyImgFile;
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

    public StoryDTO(String storyImgTag, String storyTextTag, String storyTodayTag, String storyLinkTag, String storyQuestionTag, String storySurveyTag, String storyQuizTag, String storyHashTag, String storyReferenceTag, String storyLocationTag) {
        this.storyImgTag = storyImgTag;
        this.storyTextTag = storyTextTag;
        this.storyTodayTag = storyTodayTag;
        this.storyLinkTag = storyLinkTag;
        this.storyQuestionTag = storyQuestionTag;
        this.storySurveyTag = storySurveyTag;
        this.storyQuizTag = storyQuizTag;
        this.storyHashTag = storyHashTag;
        this.storyReferenceTag = storyReferenceTag;
        this.storyLocationTag = storyLocationTag;
    }

    public static StoryDTO toStoryDTO (StoryEntity storyEntity) {
        StoryDTO storyDTO = new StoryDTO();
        storyDTO.setId(storyEntity.getId());
        storyDTO.setLoginId(storyEntity.getLoginId());
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
