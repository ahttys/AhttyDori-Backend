package com.ahttys.server.dto.auth;

import com.ahttys.server.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class Auth {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class CreateUser {
        String email;
        String name;
        String password;

        public User toEntity() {
            return User.builder().email(this.email).name(this.name).password(this.password).build();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Login {
        String email;
        String password;
    }

}
