package com.its.gramsecurity.dto;

import com.its.gramsecurity.entity.FollowEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class FollowDTO {
    private Long id;
    private String myId;
    private String yourId;
    private String yourName;
    private String yorProfile;
    private String yourProfileName;
    private int loginStatus;

    public FollowDTO(Long id, String myId, String yourId, String yourName, String yorProfile,
                     String yourProfileName, int loginStatus) {
        this.id = id;
        this.myId = myId;
        this.yourId = yourId;
        this.yourName = yourName;
        this.yorProfile = yorProfile;
        this.yourProfileName = yourProfileName;
        this.loginStatus = loginStatus;
    }



    public static FollowDTO toDTO(FollowEntity followEntity){
        FollowDTO followDTO=new FollowDTO();
        followDTO.setId(followEntity.getId());
        followDTO.setMyId(followEntity.getMyId());
        followDTO.setYourId(followEntity.getYourId());
        followDTO.setYourName(followEntity.getYourName());
        followDTO.setYourProfileName(followEntity.getYourProfileName());
        followDTO.setLoginStatus(followEntity.getLoginStatus());
        return followDTO;
    }
}
