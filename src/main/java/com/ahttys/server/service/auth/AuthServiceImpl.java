package com.ahttys.server.service.auth;

import com.ahttys.server.config.jwt.TokenProvider;
import com.ahttys.server.domain.Member.Member;
import com.ahttys.server.dto.auth.AuthDto;
import com.ahttys.server.dto.auth.KakaoAuthResponseDto;
import com.ahttys.server.dto.auth.KakaoUserInfoDto;
import com.ahttys.server.dto.common.MessageDto;
import com.ahttys.server.repository.MemberRepository;
import com.ahttys.server.service.auth.oauth.OAuth2Kakao;
import com.ahttys.server.util.error.ErrorCode;
import com.ahttys.server.util.error.exceptions.CustomException;
import com.ahttys.server.util.error.exceptions.DuplicationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final OAuth2Kakao oAuth2Kakao;

    @Override
    @Transactional
    public AuthDto.UserResponse createUser(AuthDto.CreateUser userDto) {
        if (memberRepository.existsByEmail(userDto.getEmail())) {
            throw new CustomException("이미 가입되어 있는 유저입니다.", 400);
        }

        Member newMember = memberRepository.save(userDto.toEntity(passwordEncoder));
        return AuthDto.UserResponse.builder()
                .email(newMember.getEmail())
                .name(newMember.getName())
                .build();
    }

    @Override
    public AuthDto.Token authentication(AuthDto.Login loginDto) {
        Optional<Member> user = memberRepository.findByEmail(loginDto.getEmail());
        if (user.isPresent() && passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword())) {
            String jwt = tokenProvider.createToken(user.get());
            return new AuthDto.Token(jwt);
        } else {
            throw new CustomException("로그인에 실패하였습니다.", 400);
        }
    }

    @Override
    @Transactional
    public MessageDto isValidEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new DuplicationException("존재하는 이메일 입니다.", ErrorCode.EMAIL_DUPLICATION);
        }
        return new MessageDto("사용 가능한 이메일 입니다.");
    }

    @Override
    @Transactional
    public MessageDto isValidName(String name) {
        if (memberRepository.findByName(name).isPresent()) {
            throw new DuplicationException("존재하는 닉네임 입니다.", ErrorCode.NICKNAME_DUPLICATION);
        }
        return new MessageDto("사용 가능한 닉네임 입니다.");
    }

    @Override
    @Transactional
    public AuthDto.Token oauth2KakaoWithCode(String code) {
        KakaoAuthResponseDto authorization = oAuth2Kakao.getKakaoAuthByCode(code);
        KakaoUserInfoDto info = oAuth2Kakao.getUserInfoByToken(authorization.getAccess_token());
        return new AuthDto.Token(getTokenByUserInfo(info));
    }

    @Override
    @Transactional
    public AuthDto.Token oauth2KakaoWithToken(String token) {
        KakaoUserInfoDto info = oAuth2Kakao.getUserInfoByToken(token);
        return new AuthDto.Token(getTokenByUserInfo(info));
    }

    private String getTokenByUserInfo(KakaoUserInfoDto infoDto) {
        String email = infoDto.getKakaoAccount().getEmail();
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isEmpty()) {
            Member newMember = Member.builder()
                    .email(email)
                    .name(infoDto.getProperties().getNickname())
                    .build();
            newMember.setImage(infoDto.getProperties().getProfileImage());
            newMember.setKakaoOAuth(true);

            memberRepository.save(newMember);
            return tokenProvider.createToken(newMember);
        } else {    // 이메일이 있는 경우 토큰 생성
            return tokenProvider.createToken(member.get());
        }
    }
}
