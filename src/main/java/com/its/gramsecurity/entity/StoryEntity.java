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

    @Column(length = 20, unique = true, nullable = false)
    private String memberId;

    @Column (length = 20, nullable = false)
    private String storyFileName;

    @Column
    private LocalDateTime storyCreatedTime;

    @Column
    private String storyHashtag;

    @Column
    private String storyLocation;

    @Column
    private int storyVisitStatus;

    public static StoryEntity toSaveStoryEntity(StoryDTO storyDTO){
        StoryEntity storyEntity = new StoryEntity();
        storyEntity.setMemberId(storyDTO.getMemberId());
        storyEntity.setStoryFileName(storyDTO.getStoryFileName());
        storyEntity.setStoryCreatedTime(LocalDateTime.now());
        storyEntity.setStoryHashtag(storyDTO.getStoryHashtag());
        storyEntity.setStoryLocation(storyDTO.getStoryLocation());
        return storyEntity;
    }
}
