package com.power.platform.service.impl;

import com.power.platform.service.InspirationService;
import com.power.platform.vo.InspirationVo;
import com.power.platform.vo.Result;
import org.springframework.stereotype.Service;

@Service
public class InspirationServiceImpl implements InspirationService {
    @Override
    public Result<String> saveInspiration(String inspiration, String token) {
        return null;
    }

    @Override
    public Result<InspirationVo> getAllInspiration(String token) {
        return null;
    }

    @Override
    public Result<String> deleteInspiration(Integer id, String token) {
        return null;
    }
}
