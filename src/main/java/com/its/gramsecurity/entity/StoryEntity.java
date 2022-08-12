package com.its.gramsecurity.entity;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.StoryDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.PrimitiveCharacterArrayNClobType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "story25")
public class StoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="story_id")
    private Long id;


    @Column
    private String storyFileName;

    @Column
    private String loginId;

    @Column
    private String memberName;

    @Column
    private LocalDateTime storyCreatedTime;

    @Column (length = 1000)
    private String storyImgTag;

    @Column (length = 1000)
    private String storyTextTag;

    @Column (length = 1000)
    private String storyTodayTag;

    @Column (length = 1000)
    private String storyLinkTag;

    @Column (length = 1000)
    private String storyQuestionTag;

    @Column (length = 1000)
    private String storySurveyTag;

    @Column (length = 2000)
    private String storyQuizTag;

    @Column (length = 1000)
    private String storyReferenceTag;

    @Column (length = 1000)
    private String storyHashTag;

    @Column (length = 1000)
    private String storyLocationTag;

    @Column
    private int storyVisitStatus;

    public static StoryEntity toSaveStoryEntity(StoryDTO storyDTO, MemberEntity memberEntity){
        StoryEntity storyEntity = new StoryEntity();
        storyEntity.setLoginId(memberEntity.getLoginId());
        storyEntity.setMemberName(memberEntity.getMemberName());
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
