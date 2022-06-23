package com.power.platform.service;

import com.power.platform.dao.pojo.ArticleList;
import com.power.platform.vo.ArticleListVo;
import com.power.platform.vo.Result;
import com.power.platform.vo.params.ListParam;

import java.util.List;

public interface ArticleListService {
    Result<Integer> saveList(ListParam list, String token);

    Result<List<ArticleListVo>> allLists(String token);

    Result<Integer> deleteList(Integer id, String token);
}
