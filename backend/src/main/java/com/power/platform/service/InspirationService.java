package com.power.platform.service;

import com.power.platform.vo.InspirationVo;
import com.power.platform.vo.Result;

public interface InspirationService {
    Result<String> saveInspiration(String inspiration, String token);

    Result<InspirationVo> getAllInspiration(String token);

    Result<String> deleteInspiration(Integer id, String token);
}
