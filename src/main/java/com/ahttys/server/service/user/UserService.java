package com.ahttys.server.service.user;

import com.ahttys.server.dto.auth.Auth;

public interface UserService {
    Long createUser(Auth.CreateUser userDto);
    boolean isValidEmail(String email);
    boolean isValidName(String name);
}
