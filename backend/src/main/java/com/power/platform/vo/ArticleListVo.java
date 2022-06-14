package com.power.platform.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文章列表")
public class ArticleListVo {
    @Schema(description = "列表id")
    private Integer id;
    @Schema(description = "列表的名称")
    private String name;
    @Schema(description = "文章Vo列表")
    private List<ArticleVo> articleVoList;

}
