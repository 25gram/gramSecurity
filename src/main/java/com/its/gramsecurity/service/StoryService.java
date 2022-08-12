package com.its.gramsecurity.service;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.StoryDTO;
import com.its.gramsecurity.entity.StoryEntity;
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
    public List<StoryDTO> findByMemberId(Long memberId) {
        List<StoryDTO> storyDTOList = new ArrayList<>();
        List<StoryEntity> storyEntityList = storyRepository.findByMemberId(memberId);
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


    public void save(StoryDTO storyDTO, PrincipalDetails principalDetails) throws IOException {
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
        storyDTO.setStoryFileName(storyFileName);
        storyDTO.setStoryImgTag(storyImgTag);
        storyRepository.save(StoryEntity.toSaveStoryEntity(storyDTO, principalDetails));
    }
}
