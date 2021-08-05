package com.ahttys.server.controller;

import com.ahttys.server.dto.message.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class IndexController {
    @GetMapping("/")
    public ResponseEntity<Message> startApp() {
        return new ResponseEntity<Message>(new Message("서버 시작"), HttpStatus.OK);
    }
}
