package com.ahttys.server.service.auth;

import com.ahttys.server.dto.AuthDto;
import com.ahttys.server.dto.MessageDto;

public interface AuthService {
    AuthDto.UserResponse createUser(AuthDto.CreateUser userDto);
    AuthDto.Token authentication(AuthDto.Login loginDto);
    MessageDto isValidEmail(String email);
    MessageDto isValidName(String name);
}
