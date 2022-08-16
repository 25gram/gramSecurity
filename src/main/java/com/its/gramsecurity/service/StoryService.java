package com.its.gramsecurity.service;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.FollowDTO;
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

    public StoryDTO findByLoginId(String loginId) {
        Optional<StoryEntity> byLoginId = storyRepository.findByLoginId(loginId);
        StoryDTO storyDTO = new StoryDTO();
        if (byLoginId.isPresent()) {
            storyDTO = StoryDTO.toStoryDTO(byLoginId.get());
        }
        return storyDTO;
    }

    public List<StoryDTO> findByStoryLocationTag(String storyLocationTag) {
        List<StoryEntity> storyEntityList = storyRepository.findByStoryLocationTag(storyLocationTag);
        List<StoryDTO> storyDTOList = new ArrayList<>();
        for (StoryEntity story : storyEntityList) {
            storyDTOList.add(StoryDTO.toStoryDTO(story));
        }
        return storyDTOList;
    }

    public Long save(StoryDTO storyDTO, String loginId) {
        MemberEntity memberEntity = new MemberEntity();
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByLoginId(loginId);
        if (optionalMemberEntity.isPresent()) {
            memberEntity = optionalMemberEntity.get();
        }
        return storyRepository.save(StoryEntity.toSaveStoryEntity(storyDTO, memberEntity)).getId();
    }

    public void saveFile(StoryDTO storyDTO) throws IOException {
        Optional<StoryEntity> storyEntityOptional = storyRepository.findById(storyDTO.getId());
        if (storyEntityOptional.isPresent()) {
            StoryEntity storyEntity = storyEntityOptional.get();

            MultipartFile storyFile = storyDTO.getStoryFile();
            MultipartFile storyImgFile = storyDTO.getStoryImgFile();
            String storyFileName = storyFile.getOriginalFilename();
            String storyImgName = storyImgFile.getOriginalFilename();
            if (!storyFile.isEmpty()) {
                storyFileName = System.currentTimeMillis() + "-" + storyFileName;
                String savePath = "C:\\springboot_img\\" + storyFileName;
                storyFile.transferTo(new File(savePath));
            }
            if (!storyImgFile.isEmpty()) {
                storyImgName = System.currentTimeMillis() + "-" + storyImgName;
                String savePath = "C:\\springboot_img\\" + storyImgName;
                storyImgFile.transferTo(new File(savePath));
            }
            storyEntity.setStoryFileName(storyFileName);
            storyEntity.setStoryImgName(storyImgName);
            storyEntity.setMemberProfileName(storyDTO.getMemberProfileName());
            storyRepository.save(storyEntity);
        }

    }

    public List<StoryDTO> storyView(List<FollowDTO> followDTOList) {
        List<StoryDTO> storyDTOList = new ArrayList<>();
        for (int i = 0; i < followDTOList.size(); i++) {

            String storyFollowingId = followDTOList.get(i).getMyId();
            Optional<StoryEntity> byLoginId = storyRepository.findByLoginId(storyFollowingId);
            System.out.println("StoryService.storyView");
            System.out.println("storyFollowingId = " + storyFollowingId);
            if (byLoginId.isPresent()) {
                StoryDTO storyDTO = StoryDTO.toStoryDTO(byLoginId.get());
                storyDTOList.add(storyDTO);
            }
        }
        return storyDTOList;
    }
}
