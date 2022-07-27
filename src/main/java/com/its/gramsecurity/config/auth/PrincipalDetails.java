package com.its.gramsecurity.config.auth;

import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.MemberEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
    private Map<String,Object> attributes;

    private MemberDTO memberDTO;

    private MemberEntity memberEntity;

    public PrincipalDetails(MemberDTO memberDTO){
        this.memberDTO=memberDTO;
    }

    public PrincipalDetails(MemberDTO memberDTO,Map<String,Object>attributes){
        this.memberDTO=memberDTO;
        this.attributes=attributes;
    }



    public Collection<? extends GrantedAuthority>getAuthorities(){
        Collection<GrantedAuthority>collect=new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return memberDTO.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getName(){
        return memberDTO.getMemberId();
    }

    @Override
    public String getPassword() {
        return memberDTO.getMemberPassword();
    }

    @Override
    public String getUsername() {
        return memberDTO.getMemberId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
    @Override
    public Map<String,Object>getAttributes(){
        return attributes;
    }
}
