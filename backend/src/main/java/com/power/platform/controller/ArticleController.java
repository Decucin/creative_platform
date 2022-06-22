package com.power.platform.controller;

import com.power.platform.dao.pojo.ArticleList;
import com.power.platform.dao.pojo.Inspiration;
import com.power.platform.service.*;
import com.power.platform.utils.JWTTokenUtils;
import com.power.platform.vo.ArticleVo;
import com.power.platform.vo.InspirationVo;
import com.power.platform.vo.Result;
import com.power.platform.vo.params.ArticleParam;
import com.power.platform.vo.params.InspirationParam;
import com.power.platform.vo.params.ListParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("articles")
@Api(tags = {"文章模块"})
public class ArticleController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ArticleListService listService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private InspirationService inspirationService;


    @PostMapping("save")
    @ApiOperation("保存文章")
    public Result<Integer> saveArticle(@RequestBody @ApiParam ArticleParam articleParam,
                                       @RequestHeader(JWTTokenUtils.TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return articleService.saveArticle(articleParam, token);
    }

    @GetMapping("all")
    @ApiOperation("全部文章")
    public Result<List<ArticleVo>> allArticle(@RequestHeader(value = JWTTokenUtils.TOKEN_HEADER, required = false) @ApiParam(hidden = true) String token){
        if(token == null || !redisTemplate.hasKey(token)){
            return Result.success(new ArrayList<ArticleVo>());
        }
        return articleService.allArticle(token);
    }

    @GetMapping("{id}")
    @ApiOperation("查看文章")
    public Result<ArticleVo> getArticle(@PathVariable("id") @ApiParam(value = "文章id") Integer id,
                                        @RequestHeader(JWTTokenUtils.TOKEN_HEADER) @ApiParam(hidden = true) String token){
        if(token == null || !redisTemplate.hasKey(token)){
            return Result.success(new ArticleVo());
        }
        return articleService.getArticle(id, token);
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除文章")
    public Result<Integer> deleteArticle(@PathVariable("id") @ApiParam(value = "文章id") Integer id,
                                        @RequestHeader(JWTTokenUtils.TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return articleService.deleteArticle(id, token);
    }

    @PostMapping("inspirations/save")
    @ApiOperation("保存灵感")
    public Result<Integer> saveInspiration(@RequestBody @ApiParam(value = "灵感") InspirationParam inspirationParam,
                                          @RequestHeader(JWTTokenUtils.TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return inspirationService.saveInspiration(inspirationParam, token);
    }

    @GetMapping("inspirations/all")
    @ApiOperation("查看所有灵感")
    public Result<List<InspirationVo>> getAllInspiration(@RequestHeader(value = JWTTokenUtils.TOKEN_HEADER, required = false) @ApiParam(hidden = true) String token){
        if(token == null || !redisTemplate.hasKey(token)){
            return Result.success(new ArrayList<Inspiration>());
        }
        return inspirationService.getAllInspiration(token);
    }

    @DeleteMapping("inspirations/{id}")
    @ApiOperation("删除灵感")
    public Result<Integer> deleteInspiration(@PathVariable("id") @ApiParam(value = "灵感id") Integer id,
                                            @RequestHeader(JWTTokenUtils.TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return inspirationService.deleteInspiration(id, token);
    }

    @PostMapping("lists/save")
    @ApiOperation("保存文章列表")
    public Result<Integer> addList(@RequestBody @ApiParam(value = "文章列表名") ListParam list,
                                    @RequestHeader(value = JWTTokenUtils.TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return listService.saveList(list, token);
    }

    @GetMapping("lists/all")
    @ApiOperation("查看所有文章列表")
    public Result<List<ArticleList>> allLists(@RequestHeader(value = JWTTokenUtils.TOKEN_HEADER, required = false) @ApiParam(hidden = true) String token){
        if(token == null || !redisTemplate.hasKey(token)){
            return Result.success(new ArrayList<ArticleList>());
        }
        return listService.allLists(token);
    }

    @DeleteMapping("lists/{id}")
    @ApiOperation("删除文章列表")
    public Result<Integer> deleteList(@PathVariable("id") @ApiParam(value = "文章列表id") Integer id,
                                      @RequestHeader(JWTTokenUtils.TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return listService.deleteList(id, token);
    }
}
