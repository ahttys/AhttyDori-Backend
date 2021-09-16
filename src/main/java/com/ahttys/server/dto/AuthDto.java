package com.ahttys.server.dto;

import com.ahttys.server.domain.user.Member;
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

//        private String randomPasswordGenerator() {
//            String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
//                    + "0123456789"
//                    + "abcdefghijklmnopqrstuvxyz"
//                    + "!@#$%^&*-?";
//
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < 12; i++) {
//                int idx = (int) (alphaNumericString.length() * Math.random());
//                sb.append(alphaNumericString.charAt(idx));
//            }
//            return sb.toString();
//        }
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
