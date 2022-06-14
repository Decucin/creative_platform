package com.power.platform.service;

import com.power.platform.vo.Result;
import com.power.platform.vo.params.LoginParam;

public interface LoginService {

    Result<String> login(LoginParam loginParam);

    Result<String> logout(String token);

    Result<String> registry(LoginParam loginParam);

}
