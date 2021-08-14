package com.ahttys.server.dto;

import com.ahttys.server.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthDto {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class CreateUser {
        @NotNull
        private String email;
        @NotNull
        private String name;
        @NotNull
        private String password;

        public User toEntity(PasswordEncoder passwordEncoder) {
            return User.builder().email(this.email)
                    .name(this.name)
                    .password(passwordEncoder.encode(this.password))
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
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
