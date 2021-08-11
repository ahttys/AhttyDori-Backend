package com.ahttys.server.util.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404, "PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500, "SERVER ERROR"),
    EMAIL_DUPLICATION(400, "사용중인 이메일 입니다."),
    NICKNAME_DUPLICATION(400, "사용중인 닉네임 입니다.");


    private int status;
    private String message;
}
