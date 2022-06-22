package com.power.platform.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("保存灵感需要提交的参数")
public class InspirationParam {
    @Schema(description = "灵感id")
    private Integer id;
    @Schema(description = "灵感")
    private String inspiration;
}
