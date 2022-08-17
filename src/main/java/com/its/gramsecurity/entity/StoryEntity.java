package com.its.gramsecurity.entity;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.StoryDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.PrimitiveCharacterArrayNClobType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column
    private String storyImgName;

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

    @Column (length = 3000)
    private String storySurveyTag;

    @Column (length = 3000)
    private String storyQuizTag;

    @Column (length = 1000)
    private String storyReferenceTag;

    @Column (length = 1000)
    private String storyHashTag;

    @Column (length = 1000)
    private String storyLocationTag;

    @Column
    private int storyVisitStatus;

    @Column
    private String memberProfileName;

    @OneToMany(mappedBy = "storyEntity",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<StoryViewEntity> storyEntityList=new ArrayList<>();
    public static StoryEntity toSaveStoryEntity(StoryDTO storyDTO, MemberEntity memberEntity){
        StoryEntity storyEntity = new StoryEntity();
        storyEntity.setLoginId(memberEntity.getLoginId());
        storyEntity.setMemberName(memberEntity.getMemberName());
        storyEntity.setStoryCreatedTime(LocalDateTime.now());
        storyEntity.setStoryImgTag(storyDTO.getStoryImgTag());
        storyEntity.setStoryTextTag(storyDTO.getStoryTextTag());
        storyEntity.setStoryTodayTag(storyDTO.getStoryTodayTag());
        storyEntity.setStoryLinkTag(storyDTO.getStoryLinkTag());
        storyEntity.setStoryQuestionTag(storyDTO.getStoryQuestionTag());
        storyEntity.setStorySurveyTag(storyDTO.getStorySurveyTag());
        storyEntity.setStoryQuizTag(storyDTO.getStoryQuizTag());
        storyEntity.setStoryReferenceTag(storyDTO.getStoryReferenceTag());
        storyEntity.setStoryHashTag(storyDTO.getStoryHashTag());
        storyEntity.setStoryLocationTag(storyDTO.getStoryLocationTag());
        storyEntity.setMemberProfileName(storyDTO.getMemberProfileName());
        return storyEntity;
    }
}
