package com.ahttys.server.controller;

import com.ahttys.server.dto.AuthDto;
import com.ahttys.server.dto.MessageDto;
import com.ahttys.server.service.auth.AuthService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private AuthService authService;

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = AuthDto.Token.class))),
            @ApiResponse(responseCode = "400", description = "실패", content = @Content(schema = @Schema(implementation = MessageDto.class)))
    })
    public ResponseEntity<AuthDto.Token> login(@RequestBody AuthDto.Login loginDto) {
        return ResponseEntity.ok(authService.authentication(loginDto));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = AuthDto.UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "실패", content = @Content(schema = @Schema(implementation = MessageDto.class)))
    })
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody AuthDto.CreateUser userDto) {
        if (userDto.getEmail().isEmpty() || userDto.getPassword().isEmpty() || userDto.getName().isEmpty()) {
            return new ResponseEntity<>(new MessageDto("회원가입에 실패하였습니다."), HttpStatus.BAD_REQUEST);
        }
        AuthDto.UserResponse userResponse = authService.createUser(userDto);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용가능한 데이터", content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "실패", content = @Content(schema = @Schema(implementation = MessageDto.class)))
    })
    @GetMapping("/duplicate")
    public ResponseEntity<?> checkDuplicate(@Email @RequestParam(value = "email", defaultValue = "") String email,
                                                     @RequestParam(value = "name", defaultValue = "") String name) {
        if (!email.isEmpty()) {
            return new ResponseEntity<>(authService.isValidEmail(email), HttpStatus.OK);
        } else if (!name.isEmpty()) {
            return new ResponseEntity<>(authService.isValidName(name), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageDto("값을 입력해야 합니다."), HttpStatus.BAD_REQUEST);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카카오 로그인 성공", content = @Content(schema = @Schema(implementation = AuthDto.UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "실패", content = @Content(schema = @Schema(implementation = MessageDto.class)))
    })
    @GetMapping("/kakao")
    public ResponseEntity<?> loginWithKakaoOauth(@RequestParam(value = "code") String code) {
        if (code.isEmpty()) {
            return new ResponseEntity<>(new MessageDto("Code가 없습니다."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
