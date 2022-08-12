package com.its.gramsecurity.service;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.dto.StoryDTO;
import com.its.gramsecurity.entity.MemberEntity;
import com.its.gramsecurity.entity.StoryEntity;
import com.its.gramsecurity.repository.MemberRepository;
import com.its.gramsecurity.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoryService {
    private final StoryRepository storyRepository;
    private final MemberRepository memberRepository;
    public List<StoryDTO> findByLoginId(String loginId) {
        List<StoryDTO> storyDTOList = new ArrayList<>();
        List<StoryEntity> storyEntityList = storyRepository.findByLoginId(loginId);
        for(StoryEntity story: storyEntityList) {
            storyDTOList.add(StoryDTO.toStoryDTO(story));
        }
        return storyDTOList;
    }

    public List<StoryDTO> findByStoryLocationTag(String storyLocationTag) {
        List<StoryEntity> storyEntityList = storyRepository.findByStoryLocationTag(storyLocationTag);
        List<StoryDTO> storyDTOList = new ArrayList<>();
        for(StoryEntity story: storyEntityList){
            storyDTOList.add(StoryDTO.toStoryDTO(story));
        } return storyDTOList;
    }

    public Long save(StoryDTO storyDTO, String loginId){
        MemberEntity memberEntity = new MemberEntity();
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByLoginId(loginId);
        if(optionalMemberEntity.isPresent()){
            memberEntity = optionalMemberEntity.get();
        }
        return storyRepository.save(StoryEntity.toSaveStoryEntity(storyDTO, memberEntity)).getId();
    }

    public void saveFile(StoryDTO storyDTO) throws IOException {
        Optional<StoryEntity> storyEntityOptional = storyRepository.findById(storyDTO.getId());
        if(storyEntityOptional.isPresent()){
            StoryEntity storyEntity = storyEntityOptional.get();

            MultipartFile storyFile = storyDTO.getStoryFile();
            MultipartFile storyImgFile = storyDTO.getStoryImgFile();
            String storyFileName = storyFile.getOriginalFilename();
            String storyImgTag = storyImgFile.getOriginalFilename();
            if(!storyFile.isEmpty()){
                storyFileName = System.currentTimeMillis()+"-"+ storyFileName;
                String savePath="C:\\springboot_img\\"+storyFileName;
                storyFile.transferTo(new File(savePath));
            }
            if(!storyImgFile.isEmpty()){
                storyImgTag = System.currentTimeMillis()+"-"+storyImgTag;
                String savePath="C:\\springboot_img\\"+storyImgTag;
                storyImgFile.transferTo(new File(savePath));
            }
            storyEntity.setStoryFileName(storyFileName);
            storyEntity.setStoryImgTag(storyImgTag);
            storyRepository.save(storyEntity);
        }

    }
}
