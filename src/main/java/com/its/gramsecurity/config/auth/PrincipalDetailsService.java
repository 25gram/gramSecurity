package com.its.gramsecurity.config.auth;

import com.its.gramsecurity.Repository.MemberRepository;
import com.its.gramsecurity.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        MemberEntity memberEntity = memberRepository.findByMemberId(memberId);
        if (memberEntity != null) {
            return new PrincipalDetails(memberEntity);
        }
        return null;
    }
}
