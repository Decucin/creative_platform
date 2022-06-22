package com.power.platform.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultEnum {
    NOT_CONTAIN(404, "对应的id不存在"),
    USER_ALREADY_EXIST(403, "用户已存在"),
    ILLEGAL_TOKEN(402, "Token illegal"),
    PASSWORD_ERROR(401, "密码错误"),
    USER_NOT_EXIST(400, "用户不存在"),
    TRY_AGAIN(500, "请重试"),
    NOT_YOUR_ARTICLE(501, "不能操作不是你的文章"),
    NOT_YOUR_INS(502, "不能操作不是你的灵感"),
    NOT_YOUR_LIST(503, "不能操作不是你的列表");


    Integer code;
    String msg;

}
