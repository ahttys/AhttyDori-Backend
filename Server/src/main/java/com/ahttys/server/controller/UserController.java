package com.ahttys.server.controller;

import com.ahttys.server.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private UserRepository userRepository;
}
