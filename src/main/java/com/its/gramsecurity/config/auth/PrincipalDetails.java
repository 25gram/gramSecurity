package com.its.gramsecurity.config.auth;

import com.its.gramsecurity.entity.MemberEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
    private Map<String,Object> attributes;

    private MemberEntity memberEntity;

    public PrincipalDetails(MemberEntity memberEntity){
        this.memberEntity=memberEntity;
    }

    public PrincipalDetails(MemberEntity memberEntity,Map<String,Object>attributes){
        this.memberEntity=memberEntity;
        this.attributes=attributes;
    }
    public Collection<? extends GrantedAuthority>getAuthorities(){
        Collection<GrantedAuthority>collect=new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return memberEntity.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getName(){
        return null;
    }

    @Override
    public String getPassword() {
        return memberEntity.getMemberPassword();
    }

    @Override
    public String getUsername() {
        return memberEntity.getMemberId();
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
        return true;
    }
    @Override
    public Map<String,Object>getAttributes(){
        return attributes;
    }
}
