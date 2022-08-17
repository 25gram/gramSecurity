package com.its.gramsecurity.entity;

import com.its.gramsecurity.dto.StoryViewDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "storyView_entity")
public class StoryViewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login_id")
    @JoinColumn (name = "story_id")
    private StoryEntity storyEntity;

    public static StoryViewEntity toStoryViewEntity(StoryViewDTO storyViewDTO, StoryEntity storyEntity) {
        StoryViewEntity storyView = new StoryViewEntity();
        storyView.setStoryEntity(storyEntity);
        storyView.setStoryEntity(storyEntity);
        return storyView;
    }
}
