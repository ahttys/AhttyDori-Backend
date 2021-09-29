package com.ahttys.server.controller;

import com.ahttys.server.dto.member.MemberDto;
import com.ahttys.server.dto.common.MessageDto;
import com.ahttys.server.service.member.MemberService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("member")
public class MemberController {
    private final MemberService memberService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 조회 성공", content = @Content(schema = @Schema(implementation = MemberDto.class))),
            @ApiResponse(responseCode = "404", description = "조회 실패", content = @Content(schema = @Schema(implementation = MessageDto.class)))
    })
    @GetMapping("")
    public ResponseEntity<?> getUserById( @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(memberService.findMemberById(token.substring(7)));
    }
}
