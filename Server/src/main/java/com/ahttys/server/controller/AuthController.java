package com.ahttys.server.controller;

import com.ahttys.server.dto.auth.Auth;
import com.ahttys.server.dto.message.Message;
import com.ahttys.server.service.user.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<Message> join(@RequestBody Auth.CreateUser userDto) {
        Long id = authService.createUser(userDto);

        if (id == 0) {
            return new ResponseEntity<Message>(new Message("회원가입에 실패했습니다."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Message>(new Message("회원가입에 성공했습니다."), HttpStatus.CREATED);
    }
}
