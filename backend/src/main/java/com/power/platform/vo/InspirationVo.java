package com.power.platform.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("灵感信息")
public class InspirationVo {
    @Schema(description = "灵感id")
    private Integer id;
    @Schema(description = "灵感信息")
    private String inspiration;
}
