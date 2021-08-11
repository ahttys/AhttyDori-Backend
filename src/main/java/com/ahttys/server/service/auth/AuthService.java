package com.ahttys.server.service.auth;

import com.ahttys.server.domain.user.User;
import com.ahttys.server.dto.auth.AuthDto;
import com.ahttys.server.dto.message.Message;

public interface AuthService {
    AuthDto.UserResponse createUser(AuthDto.CreateUser userDto);
    AuthDto.Token authentication(AuthDto.Login loginDto);
    Message isValidEmail(String email);
    Message isValidName(String name);
}
