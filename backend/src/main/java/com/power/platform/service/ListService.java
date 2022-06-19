package com.power.platform.service;

import com.power.platform.dao.pojo.ArticleList;
import com.power.platform.vo.Result;

import java.util.List;

public interface ListService {
    Result<Integer> addList(String listName, String token);

    Result<List<ArticleList>> allLists(String token);

    Result<Integer> deleteList(Integer id, String token);
}
