package com.power.platform.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultEnum {

    ILLEGAL_TOKEN(402, "token不合法"),
    PASSWORD_ERROR(401, "密码错误"),
    USER_NOT_EXIST(400, "用户不存在"),
    TRY_AGAIN(500, "请重试"),
    USER_ALREADY_EXIST(403, "用户已存在");


    Integer code;
    String msg;

}
