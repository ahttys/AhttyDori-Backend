package com.ahttys.server.controller;

import com.ahttys.server.dto.auth.Auth;
import com.ahttys.server.dto.message.Message;
import com.ahttys.server.service.user.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;

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

    @GetMapping("/duplicate")
    public ResponseEntity<Message> checkDuplicate(@Email @RequestParam(value = "email", defaultValue = "") String email,
                                                  @RequestParam(value = "name", defaultValue = "") String name) {
        if (!email.isEmpty()) {
            if(authService.isValidEmail(email)){
                return new ResponseEntity<Message>(new Message("사용 가능한 이메일 입니다."), HttpStatus.OK);
            }else {
                return new ResponseEntity<Message>(new Message("사용 불가능한 이메일 입니다."), HttpStatus.BAD_REQUEST);
            }
        } else if (!name.isEmpty()) {
            if (authService.isValidName(name)) {
                return new ResponseEntity<Message>(new Message("사용 가능한 닉네임 입니다."), HttpStatus.OK);
            } else {
                return new ResponseEntity<Message>(new Message("사용 불가능한 닉네임 입니다."), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<Message>(new Message("값을 입력해야 합니다."), HttpStatus.BAD_REQUEST);
    }
}
