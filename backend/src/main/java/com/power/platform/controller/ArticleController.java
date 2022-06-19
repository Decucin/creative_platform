package com.power.platform.controller;

import com.power.platform.dao.pojo.ArticleList;
import com.power.platform.service.*;
import com.power.platform.utils.JWTTokenUtils;
import com.power.platform.vo.ArticleVo;
import com.power.platform.vo.InspirationVo;
import com.power.platform.vo.Result;
import com.power.platform.vo.ResultEnum;
import com.power.platform.vo.params.ArticleParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articles")
@Api(tags = {"文章模块"})
public class ArticleController {

    @Autowired
    private ListService listService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private InspirationService inspirationService;

    private static final String TOKEN_HEADER = "Authorization";

    @PostMapping("add")
    @ApiOperation("保存文章")
    public Result<Integer> saveArticle(@RequestBody @ApiParam ArticleParam articleParam,
                                       @RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        try{
            JWTTokenUtils.getIdByToken(token);
        }catch (Exception e){
            return Result.fail(ResultEnum.ILLEGAL_TOKEN);
        }
        return articleService.saveArticle(articleParam, token);
    }

    @GetMapping("{id}")
    @ApiOperation("查看文章")
    public Result<ArticleVo> getArticle(@PathVariable("id") @ApiParam(value = "文章id") Integer id,
                                        @RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return articleService.getArticle(id, token);
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除文章")
    public Result<String> deleteArticle(@PathVariable("id") @ApiParam(value = "文章id") Integer id,
                                        @RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return articleService.deleteArticle(id, token);
    }

    @PostMapping("inspirations/add")
    @ApiOperation("保存灵感")
    public Result<String> saveInspiration(@RequestBody @ApiParam(value = "灵感") String inspiration,
                                          @RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return inspirationService.saveInspiration(inspiration, token);
    }

    @GetMapping("inspirations/all")
    @ApiOperation("查看所有灵感")
    public Result<InspirationVo> getAllInspiration(@RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return inspirationService.getAllInspiration(token);
    }

    @DeleteMapping("inspirations/{id}")
    @ApiOperation("删除灵感")
    public Result<String> deleteInspiration(@PathVariable("id") @ApiParam(value = "灵感id") Integer id,
                                            @RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return inspirationService.deleteInspiration(id, token);
    }

    @PostMapping("lists/add")
    @ApiOperation("新增文章列表")
    public Result<Integer> addList(@RequestBody @ApiParam(value = "文章列表名") String listName,
                                    @RequestHeader(value = TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return listService.addList(listName, token);
    }

    @GetMapping("lists/all")
    @ApiOperation("查看所有文章列表")
    public Result<List<ArticleList>> allLists(@RequestHeader(value = TOKEN_HEADER, required = false) @ApiParam(hidden = true) String token){
        return listService.allLists(token);
    }

    @DeleteMapping("lists/{id}")
    @ApiOperation("删除文章列表")
    public Result<Integer> deleteList(@PathVariable("id") @ApiParam(value = "文章列表id") Integer id,
                                      @RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return listService.deleteList(id, token);
    }
}
