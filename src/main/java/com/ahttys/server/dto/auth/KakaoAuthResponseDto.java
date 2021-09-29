package com.ahttys.server.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoAuthResponseDto {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;
}
