package com.power.platform.vo;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("返回结果")
public class Result<T> {
    @Schema(description = "返回结果状态码")
    private Integer code;
    @Schema(description = "返回结果信息")
    private String msg;
    @Schema(description = "返回结果数据")
    private T data;

    public static Result success(Object data){
        return new Result(200, "success", data);
    }

    public static Result fail(ResultEnum resultEnum){
        Result<Object> result = new Result<>();
        result.code = resultEnum.code;
        result.msg = resultEnum.msg;
        return result;
    }

}
