package com.its.gramsecurity.config.auth;

import com.its.gramsecurity.repository.MemberRepository;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Optional<MemberEntity>optionalMemberEntity=memberRepository.findByMemberId(memberId);
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity=optionalMemberEntity.get();
            return new PrincipalDetails(MemberDTO.toDTO(memberEntity));
        }
        return null;
    }
}
