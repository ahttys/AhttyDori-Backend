package com.ahttys.server.service.auth;

import com.ahttys.server.config.jwt.JwtFilter;
import com.ahttys.server.config.jwt.TokenProvider;
import com.ahttys.server.domain.user.User;
import com.ahttys.server.dto.auth.AuthDto;
import com.ahttys.server.dto.message.Message;
import com.ahttys.server.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public AuthDto.UserResponse createUser(AuthDto.CreateUser userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        User newUser = userRepository.save(userDto.toEntity(passwordEncoder));
        return AuthDto.UserResponse.builder()
                .email(newUser.getEmail())
                .name(newUser.getName())
                .build();
    }

    @Override
    public AuthDto.Token authentication(AuthDto.Login loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new AuthDto.Token(jwt);
    }

    @Override
    @Transactional
    public Message isValidEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("존재하는 이메일 입니다.");
        }
        return new Message("사용 가능한 이메일 입니다.");
    }

    @Override
    @Transactional
    public Message isValidName(String name) {
        if (userRepository.findByName(name).isPresent()) {
            throw new RuntimeException("존재하는 닉네임 입니다.");
        }
        return new Message("사용 가능한 닉네임 입니다.");
    }
}
