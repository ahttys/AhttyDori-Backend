package com.ahttys.server.dto.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String name;
    private String image;
    private boolean kakaoOAuth;
    private boolean appleOAuth;
}
