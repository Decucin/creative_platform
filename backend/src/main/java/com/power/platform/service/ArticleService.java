package com.power.platform.service;

import com.power.platform.vo.ArticleVo;
import com.power.platform.vo.Result;
import com.power.platform.vo.params.ArticleParam;

import java.util.List;

public interface ArticleService {
    Result<Integer> saveArticle(ArticleParam articleParam, String token);

    Result<ArticleVo> getArticle(Integer id, String token);

    Result<Integer> deleteArticle(Integer id, String token);

    Result<List<ArticleVo>> allArticle(String token);
}
