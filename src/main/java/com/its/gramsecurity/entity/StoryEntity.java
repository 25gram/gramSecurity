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
    private String storyHashtag;

    @Column
    private String storyLocation;

    @Column
    private int storyVisitStatus;

    public static StoryEntity toSaveStoryEntity(StoryDTO storyDTO){
        StoryEntity storyEntity = new StoryEntity();
        storyEntity.setMemberName(storyDTO.getMemberName());
        storyEntity.setStoryFileName(storyDTO.getStoryFileName());
        storyEntity.setStoryCreatedTime(LocalDateTime.now());
        storyEntity.setStoryHashtag(storyDTO.getStoryHashtag());
        storyEntity.setStoryLocation(storyDTO.getStoryLocation());
        return storyEntity;
    }
}
