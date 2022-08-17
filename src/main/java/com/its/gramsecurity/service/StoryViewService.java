package com.its.gramsecurity.service;

import com.its.gramsecurity.dto.StoryViewDTO;
import com.its.gramsecurity.entity.MemberEntity;
import com.its.gramsecurity.entity.StoryEntity;
import com.its.gramsecurity.entity.StoryViewEntity;
import com.its.gramsecurity.repository.MemberRepository;
import com.its.gramsecurity.repository.StoryRepository;
import com.its.gramsecurity.repository.StoryViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoryViewService {
    private final StoryViewRepository storyViewRepository;
    private final StoryRepository storyRepository;
    private final MemberRepository memberRepository;
    public void storyViewSave(String loginId) {
        StoryViewEntity storyViewEntity = new StoryViewEntity();
        List<StoryEntity> storyEntityList = storyRepository.findByLoginId(loginId);
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByLoginId(loginId);
        MemberEntity memberEntity = optionalMemberEntity.get();
        for(StoryEntity story: storyEntityList){
            if(story!=null){
                storyViewEntity = StoryViewEntity.toStoryViewEntity(story, memberEntity);
            }
        }
        storyViewRepository.save(storyViewEntity);
    }
    public boolean findByStoryIdAndLoginId(Long id, String loginId){
        Optional<StoryViewEntity> optionalStoryViewEntity = storyViewRepository.findByStoryIdAndLoginId(id, loginId);
        if(optionalStoryViewEntity.isPresent()){
            return true;
        } else {
            return false;
        }
    }
}
