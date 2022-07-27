package com.its.gramsecurity.config.oauth;

import com.its.gramsecurity.repository.MemberRepository;
import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.MemberEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws
            OAuth2AuthenticationException{
        System.out.println("PrincipalOauth2UserService.loadUser");
        OAuth2User oAuth2User=super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId=oAuth2User.getAttribute("sub");
        String memberId=provider+"_"+providerId;
        String memberPassword = oAuth2User.getAttribute("memberPassword");
        String memberEmail=oAuth2User.getAttribute("email");
        String role ="ROLE_USER";

        MemberDTO memberDTO=new MemberDTO();
        Optional<MemberEntity>optionalMemberEntity=memberRepository.findByMemberId(memberId);
        if (optionalMemberEntity.isPresent()){
            MemberEntity member=optionalMemberEntity.get();
            memberDTO=MemberDTO.toDTO(member);
        }


        if(optionalMemberEntity.isEmpty()){
            memberDTO=MemberDTO.builder()
                    .memberId(memberId)
                    .memberPassword(memberPassword)
                    .memberEmail(memberEmail)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            memberRepository.save(MemberEntity.toSaveEntity(memberDTO));

        }
        return new PrincipalDetails(memberDTO,oAuth2User.getAttributes());
    }
}
