package com.ahttys.server.service.user;

import com.ahttys.server.dto.auth.Auth;

public interface AuthService {
    public Long createUser(Auth.CreateUser userDto);
    public boolean isValidEmail(String email);
    public  boolean isValidName(String name);
}
