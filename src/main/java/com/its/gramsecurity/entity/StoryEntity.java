package com.its.gramsecurity.entity;

import com.its.gramsecurity.dto.StoryDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table
public class StoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 500, unique = true, nullable = false)
    private String memberName;

    @Column (length = 500, nullable = false)
    private String storyFileName;

    @Column
    private LocalDateTime storyCreatedTime;

    @Column
    private String storyImgTag;

    @Column
    private String storyTextTag;

    @Column
    private String storyTodayTag;

    @Column
    private String storyLinkTag;

    @Column
    private String storyQuestionTag;

    @Column
    private String storySurveyTag;

    @Column
    private String storyQuizTag;

    @Column
    private String storyReferenceTag;

    @Column
    private String storyHashTag;

    @Column
    private String storyLocationTag;

    @Column
    private int storyVisitStatus;

    public static StoryEntity toSaveStoryEntity(StoryDTO storyDTO){
        StoryEntity storyEntity = new StoryEntity();
        storyEntity.setMemberName(storyDTO.getMemberName());
        storyEntity.setStoryFileName(storyDTO.getStoryFileName());
        storyEntity.setStoryCreatedTime(LocalDateTime.now());
        storyEntity.setStoryTextTag(storyDTO.getStoryTextTag());
        storyEntity.setStoryTodayTag(storyDTO.getStoryTodayTag());
        storyEntity.setStoryLinkTag(storyDTO.getStoryLinkTag());
        storyEntity.setStoryQuestionTag(storyDTO.getStoryQuestionTag());
        storyEntity.setStorySurveyTag(storyDTO.getStorySurveyTag());
        storyEntity.setStoryQuizTag(storyDTO.getStoryQuizTag());
        storyEntity.setStoryHashTag(storyDTO.getStoryHashTag());
        storyEntity.setStoryReferenceTag(storyDTO.getStoryReferenceTag());
        storyEntity.setStoryLocationTag(storyDTO.getStoryLocationTag());
        return storyEntity;
    }
}
