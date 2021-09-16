package com.ahttys.server.service.auth;

import com.ahttys.server.domain.user.Member;
import com.ahttys.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String pk) throws UsernameNotFoundException {
        return userRepository.findById(Long.valueOf(pk))
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(pk + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getUserRole().toString());

        return new org.springframework.security.core.userdetails.User(
                String.valueOf(member.getId()),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
