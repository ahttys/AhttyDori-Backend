package com.ahttys.server.service.auth;

import com.ahttys.server.dto.auth.AuthDto;
import com.ahttys.server.dto.common.MessageDto;

public interface AuthService {
    AuthDto.UserResponse createUser(AuthDto.CreateUser userDto);
    AuthDto.Token authentication(AuthDto.Login loginDto);
    MessageDto isValidEmail(String email);
    MessageDto isValidName(String name);

    AuthDto.Token oauth2KakaoWithCode(String code);
    AuthDto.Token oauth2KakaoWithToken(String token);
}
