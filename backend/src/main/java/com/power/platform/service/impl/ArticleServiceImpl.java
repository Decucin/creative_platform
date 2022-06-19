package com.power.platform.service.impl;

import com.power.platform.service.ArticleService;
import com.power.platform.vo.ArticleVo;
import com.power.platform.vo.Result;
import com.power.platform.vo.params.ArticleParam;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Override
    public Result<Integer> saveArticle(ArticleParam articleParam, String token) {
        return null;
    }

    @Override
    public Result<ArticleVo> getArticle(Integer id, String token) {
        return null;
    }

    @Override
    public Result<String> deleteArticle(Integer id, String token) {
        return null;
    }
}
