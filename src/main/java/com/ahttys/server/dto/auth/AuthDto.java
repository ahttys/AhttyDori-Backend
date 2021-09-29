package com.ahttys.server.dto.auth;

import com.ahttys.server.domain.Member.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;

public class AuthDto {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateUser {
        @NotNull
        private String email;
        @NotNull
        private String name;
        private String password;

        public Member toEntity(PasswordEncoder passwordEncoder) {
            return Member.builder()
                    .email(this.email)
                    .name(this.name)
                    .password(passwordEncoder.encode(this.password))
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse{
        @NotNull
        private String email;
        @NotNull
        private String name;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Login {
        @NotNull
        private String email;
        @NotNull
        private String password;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Token {
        @NotNull
        private String token;
    }
}
