package com.power.platform.service.impl;

import com.power.platform.dao.pojo.ArticleList;
import com.power.platform.service.ListService;
import com.power.platform.vo.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListServiceImpl implements ListService {
    @Override
    public Result<Integer> addList(String listName, String token) {
        return null;
    }

    @Override
    public Result<List<ArticleList>> allLists(String token) {
        return null;
    }

    @Override
    public Result<Integer> deleteList(Integer id, String token) {
        return null;
    }
}
