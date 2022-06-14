package com.power.platform.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文章信息")
public class ArticleVo {
    @Schema(description = "文章id")
    private Integer id;
    @Schema(description = "文章标题")
    private Integer title;
    @Schema(description = "文章摘要")
    private String description;
    @Schema(description = "文章主体(html)")
    private String body;
}
