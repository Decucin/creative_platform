package com.power.platform.controller;

import com.power.platform.service.LoginService;
import com.power.platform.utils.JWTTokenUtils;
import com.power.platform.vo.Result;
import com.power.platform.vo.params.LoginParam;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Api(tags = "登录注册模块")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("login")
    @ApiOperation("登录接口")
    public Result<String> login(@RequestBody @ApiParam LoginParam param){
        return loginService.login(param);
    }

    @GetMapping("logout")
    @ApiOperation(value = "登出接口")
    public Result<String> logout(@RequestHeader(JWTTokenUtils.TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return loginService.logout(token);
    }

    @PostMapping("registry")
    @ApiOperation("注册接口")
    public Result<String> registry(@RequestBody @ApiParam LoginParam param){
        return loginService.registry(param);
    }

}
