package com.power.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.power.platform.dao.mapper.ArticleListMapper;
import com.power.platform.dao.pojo.ArticleList;
import com.power.platform.service.ArticleListService;
import com.power.platform.utils.JWTTokenUtils;
import com.power.platform.vo.ArticleListVo;
import com.power.platform.vo.Result;
import com.power.platform.vo.ResultEnum;
import com.power.platform.vo.params.ListParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ArticleListServiceImpl implements ArticleListService {

    @Autowired
    private ArticleListMapper articleListMapper;

    @Transactional
    @Override
    public Result<Integer> saveList(ListParam list, String token) {
        Integer authorId = JWTTokenUtils.getIdByToken(token);
        ArticleList articleList = new ArticleList();
        Integer id = list.getId();
        if(id == null){
            articleList.setName(list.getListName());
            articleList.setParentId(list.getParentId());
            articleList.setAuthorId(authorId);
            articleListMapper.insert(articleList);
        }else{
            if(!articleListMapper.selectById(id).getAuthorId().equals(authorId)){
                return Result.fail(ResultEnum.NOT_YOUR_LIST);
            }
            articleList.setId(id);
            articleList.setName(list.getListName());
            articleListMapper.updateById(articleList);
        }
        return Result.success(articleList.getId());
    }

    @Transactional
    @Override
    public Result<List<ArticleList>> allLists(String token) {
        Integer authorId = JWTTokenUtils.getIdByToken(token);
        // 用树的形式做
        // 先把全部数据查出来
        LambdaQueryWrapper<ArticleList> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleList::getAuthorId, authorId);
        List<ArticleList> articleLists = articleListMapper.selectList(queryWrapper);
        // 对这些数据进行封装
        ArrayList<ArticleListVo> res = new ArrayList<>();
        HashMap<Integer, ArticleListVo> map = new HashMap<>();
        // 这个set里面存那些已经封装好的
        Set<Integer> set = new HashSet<>();
        for(ArticleList articleList : articleLists){
            Integer id = articleList.getId();
            Integer parentId = articleList.getParentId();
            ArticleListVo articleListVo = new ArticleListVo(id, articleList.getName(), new ArrayList<>());
            map.put(id, articleListVo);
            if(parentId == 0){
                res.add(articleListVo);
                set.add(id);
            } else if (map.containsKey(parentId)) {
                map.get(parentId).getArticleListVos().add(articleListVo);
                set.add(id);
            }
        }
        for(ArticleList articleList : articleLists){
            Integer id = articleList.getId();
            if(!set.contains(id)){
                map.get(articleList.getParentId()).getArticleListVos().add(map.get(id));
            }
        }

        return Result.success(res);
    }

    @Transactional
    @Override
    public Result<Integer> deleteList(Integer id, String token) {
        ArticleList articleList = articleListMapper.selectById(id);
        if(articleList == null){
            return Result.fail(ResultEnum.NOT_CONTAIN);
        }

        if(!articleList.getAuthorId().equals(JWTTokenUtils.getIdByToken(token))){
            return Result.fail(ResultEnum.NOT_YOUR_LIST);
        }

        return Result.success(articleListMapper.deleteById(id));
    }
}
