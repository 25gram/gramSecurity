package com.its.gramsecurity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

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
}
