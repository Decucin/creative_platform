package com.power.platform.controller;

import com.power.platform.service.ArticleService;
import com.power.platform.service.BodyService;
import com.power.platform.service.InspirationService;
import com.power.platform.service.UserService;
import com.power.platform.vo.ArticleListVo;
import com.power.platform.vo.ArticleVo;
import com.power.platform.vo.InspirationVo;
import com.power.platform.vo.Result;
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
    private ArticleService articleService;

    @Autowired
    private BodyService bodyService;

    @Autowired
    private InspirationService inspirationService;

    @Autowired
    private UserService userService;

    private static final String TOKEN_HEADER = "Authorization";

    @PostMapping("add")
    @ApiOperation("保存文章")
    public Result<String> saveArticle(@RequestBody @ApiParam ArticleParam articleParam, @RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return null;
    }

    @GetMapping("{id}")
    @ApiOperation("查看文章")
    public Result<ArticleVo> getArticle(@PathVariable("id") @ApiParam(value = "文章id") Integer id, @RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return null;
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除文章")
    public Result<String> deleteArticle(@PathVariable("id") @ApiParam(value = "文章id") Integer id, @RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return null;
    }

    @PostMapping("inspirations/add")
    @ApiOperation("保存灵感")
    public Result<String> saveInspiration(@RequestBody @ApiParam(value = "灵感") String inspiration, @RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return null;
    }

    @GetMapping("inspirations/all")
    @ApiOperation("查看所有灵感")
    public Result<InspirationVo> getInspiration(@RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return null;
    }

    @DeleteMapping("inspirations/{id}")
    @ApiOperation("删除灵感")
    public Result<String> deleteInspiration(@PathVariable("id") @ApiParam(value = "灵感id") Integer id, @RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return null;
    }

    @PostMapping("lists/add")
    @ApiOperation("新增文章列表")
    public Result<String> addLists(@RequestBody @ApiParam(value = "文章列表名") String listName, @RequestHeader(value = TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return null;
    }

    @GetMapping("lists/all")
    @ApiOperation("查看所有文章列表")
    public Result<List<ArticleListVo>> allArticle(@RequestHeader(value = TOKEN_HEADER, required = false) @ApiParam(hidden = true) String token){
        return null;
    }

    @DeleteMapping("lists/{id}")
    @ApiOperation("删除文章列表")
    public Result<String> deleteLists(@PathVariable("id") @ApiParam(value = "文章列表id") Integer id, @RequestHeader(TOKEN_HEADER) @ApiParam(hidden = true) String token){
        return null;
    }
}
