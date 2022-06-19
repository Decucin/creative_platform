package com.power.platform.service;

import com.power.platform.vo.ArticleVo;
import com.power.platform.vo.Result;
import com.power.platform.vo.params.ArticleParam;

public interface ArticleService {
    Result<Integer> saveArticle(ArticleParam articleParam, String token);

    Result<ArticleVo> getArticle(Integer id, String token);

    Result<String> deleteArticle(Integer id, String token);
}
