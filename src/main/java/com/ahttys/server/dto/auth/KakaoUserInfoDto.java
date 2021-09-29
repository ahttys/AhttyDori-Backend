package com.ahttys.server.dto.auth;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class KakaoUserInfoDto {
    private Properties properties;
    private KakaoAccount kakaoAccount;

    @Getter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @NoArgsConstructor
    public static class Properties {
        private String nickname;
        private String profileImage;
    }

    @Getter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @NoArgsConstructor
    public static class KakaoAccount {
        private String email;
    }
}
