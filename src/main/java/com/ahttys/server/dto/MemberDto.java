package com.ahttys.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String name;
    private boolean kakaoOAuth;
    private boolean appleOAuth;
}
