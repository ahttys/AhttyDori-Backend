package com.ahttys.server.controller;

import com.ahttys.server.dto.MessageDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class IndexController {
    @GetMapping("/")
    @ApiOperation(value = "start server", notes = "swagger check")
    public ResponseEntity<MessageDto> startApp() {
        return new ResponseEntity<>(new MessageDto("서버 시작"), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<MessageDto> test() {
        return new ResponseEntity<>(new MessageDto("인증 완료"), HttpStatus.OK);
    }
}
