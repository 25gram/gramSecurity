package com.its.gramsecurity.entity;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.StoryDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.PrimitiveCharacterArrayNClobType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name="story_entity")
@NoArgsConstructor
public class StoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")
    private MemberEntity memberEntity;

    @Column
    private String storyFileName;

    @Column
    private String loginId;

    @Column
    private LocalDateTime storyCreatedTime;

    @Column (length = 16000)
    private String storyImgTag;

    @Column (length = 16000)
    private String storyTextTag;

    @Column (length = 16000)
    private String storyTodayTag;

    @Column (length = 16000)
    private String storyLinkTag;

    @Column (length = 16000)
    private String storyQuestionTag;

    @Column (length = 16000)
    private String storySurveyTag;

    @Column (length = 16000)
    private String storyQuizTag;

    @Column (length = 16000)
    private String storyReferenceTag;

    @Column (length = 16000)
    private String storyHashTag;

    @Column (length = 16000)
    private String storyLocationTag;

    @Column
    private int storyVisitStatus;

    public static StoryEntity toSaveStoryEntity(StoryDTO storyDTO, PrincipalDetails principalDetails,MemberEntity memberEntity){
        StoryEntity storyEntity = new StoryEntity();
        storyEntity.setMemberEntity(memberEntity);
        storyEntity.setLoginId(principalDetails.getMemberDTO().getLoginId());
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
