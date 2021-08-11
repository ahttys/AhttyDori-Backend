package com.ahttys.server.controller;

import com.ahttys.server.domain.user.User;
import com.ahttys.server.dto.auth.AuthDto;
import com.ahttys.server.dto.message.Message;
import com.ahttys.server.service.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthDto.Token> login(@Valid @RequestBody AuthDto.Login loginDto) {
        return ResponseEntity.ok(authService.authentication(loginDto));
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody AuthDto.CreateUser userDto) {
        AuthDto.UserResponse userResponse = authService.createUser(userDto);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/duplicate")
    public ResponseEntity<Message> checkDuplicate(@Email @RequestParam(value = "email", defaultValue = "") String email,
                                                  @RequestParam(value = "name", defaultValue = "") String name) {
        if (!email.isEmpty()) {
            return new ResponseEntity<Message>(authService.isValidEmail(email), HttpStatus.OK);
        } else if (!name.isEmpty()) {
            return new ResponseEntity<Message>(authService.isValidName(name), HttpStatus.OK);
        }
        return new ResponseEntity<Message>(new Message("값을 입력해야 합니다."), HttpStatus.BAD_REQUEST);
    }
}
