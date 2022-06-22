package com.power.platform.service;

import com.power.platform.vo.InspirationVo;
import com.power.platform.vo.Result;
import com.power.platform.vo.params.InspirationParam;

import java.util.List;

public interface InspirationService {
    Result<Integer> saveInspiration(InspirationParam inspirationParam, String token);

    Result<List<InspirationVo>> getAllInspiration(String token);

    Result<Integer> deleteInspiration(Integer id, String token);
}
