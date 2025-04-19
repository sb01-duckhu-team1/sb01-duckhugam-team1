package com.codeit.duckhu.domain.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode {
    EMAIL_DUPLICATION("USER_409", "이미 존재하는 이메일입니다."),
    LOGIN_INPUT_INVALID("USER_401","이메일 또는 비밀번호가 올바르지 않습니다."),
    NOT_FOUND_USER("USER_404","해당 유저가 존재하지 않습니다.");

    private final String code;
    private final String message;
}
