package com.ahttys.server.service.user;

import com.ahttys.server.domain.user.UserRepository;
import com.ahttys.server.dto.auth.Auth;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;

    @Override
    @Transactional
    public Long createUser(Auth.CreateUser userDto) {
        return userRepository.save(userDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public boolean isValidEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    @Override
    @Transactional
    public boolean isValidName(String name) {
        return userRepository.findByName(name).isEmpty();
    }
}
