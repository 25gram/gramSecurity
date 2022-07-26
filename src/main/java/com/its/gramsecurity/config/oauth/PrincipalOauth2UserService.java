package com.its.gramsecurity.config.oauth;

import com.its.gramsecurity.repository.MemberRepository;
import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.MemberEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
private final MemberRepository memberRepository;
private final HttpSession httpSession;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws
            OAuth2AuthenticationException{

        OAuth2User oAuth2User=super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId=oAuth2User.getAttribute("sub");
        String loginId=provider+"_"+providerId;
        String memberProfileName="noProfile.png";
        String memberPassword = oAuth2User.getAttribute("memberPassword");
        String memberEmail=oAuth2User.getAttribute("email");
        String memberName= oAuth2User.getAttribute("name");
        String role ="ROLE_USER";
        MemberDTO memberDTO=new MemberDTO();
        Optional<MemberEntity>optionalMemberEntity=memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isPresent()){
            MemberEntity member=optionalMemberEntity.get();
            memberDTO=MemberDTO.toDTO(member);
        }
        if(optionalMemberEntity.isEmpty()){
            memberDTO=MemberDTO.builder()
                    .loginId(loginId)
                    .memberPassword(memberPassword)
                    .memberEmail(memberEmail)
                    .memberName(memberName)
                    .memberProfileName(memberProfileName)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            memberRepository.save(MemberEntity.toSaveEntity(memberDTO));

        }
        return new PrincipalDetails(memberDTO,oAuth2User.getAttributes());
    }
}
