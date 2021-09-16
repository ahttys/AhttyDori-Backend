package com.ahttys.server.controller;

import com.ahttys.server.dto.MemberDto;
import com.ahttys.server.dto.MessageDto;
import com.ahttys.server.service.member.MemberService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("user/")
public class MemberController {
    private final MemberService memberService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 조회 성공", content = @Content(schema = @Schema(implementation = MemberDto.class))),
            @ApiResponse(responseCode = "404", description = "조회 실패", content = @Content(schema = @Schema(implementation = MessageDto.class)))
    })
    @GetMapping("{uid}")
    public ResponseEntity<?> getUserById(@PathVariable Long uid) {
        return ResponseEntity.ok(memberService.findMemberById(uid));
    }
}
