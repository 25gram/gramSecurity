package com.its.gramsecurity.service;

import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.dto.StoryDTO;
import com.its.gramsecurity.dto.StoryViewDTO;
import com.its.gramsecurity.entity.MemberEntity;
import com.its.gramsecurity.entity.StoryEntity;
import com.its.gramsecurity.entity.StoryViewEntity;
import com.its.gramsecurity.repository.MemberRepository;
import com.its.gramsecurity.repository.StoryRepository;
import com.its.gramsecurity.repository.StoryViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoryViewService {
    private final StoryViewRepository storyViewRepository;
    private final StoryRepository storyRepository;
    private final MemberRepository memberRepository;
    public Long storyViewSave(Long storyId, String loginId) {
        StoryViewEntity saveStoryView = new StoryViewEntity();
        List<StoryViewEntity> storyViewList = storyViewRepository.findAllByStoryEntityIdAndMemberEntityLoginId(storyId, loginId);
        System.out.println("========================StoryViewService/storyViewSave/storyViewList========================"+storyViewList);
        Long countStoryView=0L;
            if(storyViewList.isEmpty()) { // 스토리본적 없으면
                    Optional<StoryEntity> optionalStoryEntity = storyRepository.findById(storyId);
                    if(optionalStoryEntity.isPresent()) {
                        StoryEntity storyEntity = optionalStoryEntity.get();
                        saveStoryView.setStoryEntity(storyEntity);
                    }
                    Optional<MemberEntity> optionalMemberEntity = memberRepository.findByLoginId(loginId);
                    if(optionalMemberEntity.isPresent()) {
                        MemberEntity memberEntity = optionalMemberEntity.get();
                        saveStoryView.setMemberEntity(memberEntity);
                    }
                    storyViewRepository.save(saveStoryView).getId();
        System.out.println("========================StoryViewService/storyViewSave/saveStoryView========================"+ StoryViewDTO.toStoryViewDTOSave(saveStoryView));
            }
                    countStoryView = storyViewRepository.countByStoryEntity_Id(storyId);
        System.out.println("=======================StoryViewService/storyViewSave/countStoryView========================"+ countStoryView);
        return countStoryView;

    }
    public boolean findByStoryIdAndLoginId(Long storyId, String storyWriter){
        List<StoryViewEntity> StoryViewList = storyViewRepository.findAllByStoryEntityIdAndMemberEntityLoginId(storyId, storyWriter);
        System.out.println("=======================StoryViewService/findByStoryIdAndLoginId/storyId====================="+storyId);
        System.out.println("=====================StoryViewService/findByStoryIdAndLoginId/storyWriter==================="+storyWriter);

        if(StoryViewList.size()>0){
            return true;
        } else {
            return false;
        }
    }
}
