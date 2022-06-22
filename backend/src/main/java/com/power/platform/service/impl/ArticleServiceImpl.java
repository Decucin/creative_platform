package com.power.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.power.platform.dao.mapper.ArticleMapper;
import com.power.platform.dao.pojo.Article;
import com.power.platform.dao.pojo.Body;
import com.power.platform.service.ArticleService;
import com.power.platform.service.BodyService;
import com.power.platform.utils.JWTTokenUtils;
import com.power.platform.vo.ArticleVo;
import com.power.platform.vo.Result;
import com.power.platform.vo.ResultEnum;
import com.power.platform.vo.params.ArticleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private BodyService bodyService;

    @Transactional
    @Override
    public Result<Integer> saveArticle(ArticleParam articleParam, String token) {
        // 到这里说明token正确
        // 但是需要考虑文章是不是这个人的
        Integer authorId = JWTTokenUtils.getIdByToken(token);
        Integer id = articleParam.getId();
        Body body = new Body();
        Article article = new Article();
        // insert or update
        if(id == null){

            // insertBody
            body.setRawBody(articleParam.getBody());
            body.setHtmlBody(articleParam.getBodyHtml());
            bodyService.insertBody(body);
            Integer bodyId = body.getId();

            // insertArticle
            article.setTitle(articleParam.getTitle());
            article.setDescription(articleParam.getDescription());
            article.setBodyId(bodyId);
            article.setAuthorId(authorId);
            article.setListId(articleParam.getListId());
            articleMapper.insert(article);
        }else {
            Article art = articleMapper.selectById(id);
            if(!art.getAuthorId().equals(authorId)){
                return Result.fail(ResultEnum.NOT_YOUR_ARTICLE);
            }

            // updateBody->先查到对应的bodyId
            body.setId(art.getBodyId());
            body.setRawBody(articleParam.getBody());
            body.setHtmlBody(articleParam.getBodyHtml());
            bodyService.updateBodyById(body);

            // insertArticle
            article.setId(id);
            article.setTitle(articleParam.getTitle());
            article.setDescription(articleParam.getDescription());
            article.setListId(articleParam.getListId());
            articleMapper.updateById(article);
        }
        return Result.success(article.getId());

    }

    @Transactional
    @Override
    public Result<ArticleVo> getArticle(Integer id, String token) {
        Article article = articleMapper.selectById(id);
        Integer bodyId = article.getBodyId();
        if(!article.getAuthorId().equals(JWTTokenUtils.getIdByToken(token))){
            return Result.fail(ResultEnum.NOT_YOUR_ARTICLE);
        }
        Body body = bodyService.selectBodyById(bodyId);
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(article.getId());
        articleVo.setTitle(article.getTitle());
        articleVo.setDescription(article.getDescription());
        articleVo.setBody(body.getHtmlBody());
        articleVo.setListId(article.getListId());
        return Result.success(articleVo);
    }

    @Transactional
    @Override
    public Result<Integer> deleteArticle(Integer id, String token) {
        Article article = articleMapper.selectById(id);
        if(article == null){
            return Result.fail(ResultEnum.NOT_CONTAIN);
        }
        if(!article.getAuthorId().equals(JWTTokenUtils.getIdByToken(token))){
            return Result.fail(ResultEnum.NOT_YOUR_ARTICLE);
        }
        return Result.success(articleMapper.deleteById(id));
    }

    @Override
    public Result<List<ArticleVo>> allArticle(String token) {
        Integer authorId = JWTTokenUtils.getIdByToken(token);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getAuthorId, authorId);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        ArrayList<ArticleVo> res = new ArrayList<>();
        for(Article article : articles){
            res.add(new ArticleVo(article.getId(), article.getTitle(), null, null, article.getListId()));
        }
        return Result.success(res);
    }
}
