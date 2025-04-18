package com.codeit.duckhu.domain.user.service;

import com.codeit.duckhu.domain.user.dto.UserDto;
import com.codeit.duckhu.domain.user.dto.UserLoginRequest;
import com.codeit.duckhu.domain.user.dto.UserRegisterRequest;

public interface UserService {
    UserDto create(UserRegisterRequest userRegisterRequest);
    UserDto login(UserLoginRequest userLoginRequest);
}
