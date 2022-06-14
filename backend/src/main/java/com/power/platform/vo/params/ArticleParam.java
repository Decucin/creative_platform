package com.power.platform.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("保存文章需要提交的参数")
public class ArticleParam {
    @Schema(description = "文章标题")
    private String title;
    @Schema(description = "文章摘要")
    private String description;
    @Schema(description = "文章内容")
    private String body;
    @Schema(description = "文章内容html代码")
    private String bodyHtml;

}
