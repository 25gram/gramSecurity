package com.its.gramsecurity.config.oauth;

import com.its.gramsecurity.Repository.MemberRepository;
import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.entity.MemberEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Data
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws
            OAuth2AuthenticationException{
        OAuth2User oAuth2User=super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getClientId();
        String providerId=oAuth2User.getAttribute("sub");
        String memberId=provider+"_"+providerId;
        String memberPassword = oAuth2User.getAttribute("memberPassword");
        String memberEmail=oAuth2User.getAttribute("memberEmail");
        String role ="ROLE_USER";

        MemberEntity member=memberRepository.findByMemberId(memberId);
        if(member==null){
            member=MemberEntity.builder()
                    .memberId(memberId)
                    .memberPassword(memberPassword)
                    .memberEmail(memberEmail)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            memberRepository.save(member);
        }
        return new PrincipalDetails(member,oAuth2User.getAttributes());
    }
}
