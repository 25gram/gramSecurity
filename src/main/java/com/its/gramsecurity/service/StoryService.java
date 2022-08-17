package com.its.gramsecurity.service;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.FollowDTO;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.dto.StoryDTO;
import com.its.gramsecurity.entity.FollowEntity;
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

    public String saveFile(StoryDTO storyDTO) throws IOException {
        String loginId="";
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
            loginId = storyRepository.save(storyEntity).getLoginId();
        }
            return loginId;
    }

    public List<StoryDTO> storyView(List<FollowDTO> followDTOList) {
        List<StoryDTO> storyDTOList = new ArrayList<>();
        for (int i = 0; i < followDTOList.size(); i++) {

            String storyFollowingId = followDTOList.get(i).getMyId();
            List<StoryEntity> storyEntityList = storyRepository.findByLoginId(storyFollowingId);

            for (StoryEntity story : storyEntityList) {
                storyDTOList.add(StoryDTO.toStoryDTO(story));
            }
        }
        return storyDTOList;
    }

    public StoryDTO findById(Long id) {
        Optional<StoryEntity> byLoginId = storyRepository.findById(id);
        StoryDTO storyDTO = new StoryDTO();
        if (byLoginId.isPresent()) {
            storyDTO = StoryDTO.toStoryDTO(byLoginId.get());
        }
        return storyDTO;
    }

    public void updateProfile(MemberDTO memberDTO, String memberProfileName) {
        List<StoryEntity>storyEntityList=storyRepository.findByLoginId(memberDTO.getLoginId());
        for (int i = 0; i < storyEntityList.size(); i++) {
            storyEntityList.get(i).setMemberProfileName(memberProfileName);
            storyRepository.save(storyEntityList.get(i));
        }
    }
}
