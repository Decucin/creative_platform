package com.power.platform.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("保存列表需要提交的参数")
public class ListParam {

    @Schema(description = "列表id")
    private Integer id;
    @Schema(description = "列表名称")
    private String listName;
    @Schema(description = "父标签id")
    private Integer parentId;
}
