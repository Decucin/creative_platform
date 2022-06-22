package com.power.platform.service.impl;

import com.power.platform.dao.mapper.BodyMapper;
import com.power.platform.dao.pojo.Body;
import com.power.platform.service.BodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BodyServiceImpl implements BodyService {

    @Autowired
    private BodyMapper bodyMapper;

    @Override
    public Integer insertBody(Body body) {
        bodyMapper.insert(body);
        return body.getId();
    }

    @Override
    public Integer updateBodyById(Body body) {
        return bodyMapper.updateById(body);
    }

    @Override
    public Body selectBodyById(Integer id) {
        return bodyMapper.selectById(id);
    }
}
