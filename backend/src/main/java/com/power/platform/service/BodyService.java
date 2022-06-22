package com.power.platform.service;

import com.power.platform.dao.pojo.Body;

public interface BodyService {
    Integer insertBody(Body body);

    Integer updateBodyById(Body body);

    Body selectBodyById(Integer bodyId);
}
