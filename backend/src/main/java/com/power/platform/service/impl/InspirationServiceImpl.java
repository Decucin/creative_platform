package com.power.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.power.platform.dao.mapper.InspirationMapper;
import com.power.platform.dao.pojo.Inspiration;
import com.power.platform.service.InspirationService;
import com.power.platform.utils.JWTTokenUtils;
import com.power.platform.vo.InspirationVo;
import com.power.platform.vo.Result;
import com.power.platform.vo.ResultEnum;
import com.power.platform.vo.params.InspirationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InspirationServiceImpl implements InspirationService {

    @Autowired
    private InspirationMapper inspirationMapper;

    @Transactional
    @Override
    public Result<Integer> saveInspiration(InspirationParam inspirationParam, String token) {
        Integer authorId = JWTTokenUtils.getIdByToken(token);

        // insert
        Inspiration inspiration = new Inspiration();
        Integer id = inspirationParam.getId();
        if(id == null){
            inspiration.setInspiration(inspirationParam.getInspiration());
            inspiration.setAuthorId(authorId);
            inspirationMapper.insert(inspiration);
        }else{

            if(!inspirationMapper.selectById(id).getAuthorId().equals(authorId)){
                return Result.fail(ResultEnum.NOT_YOUR_INS);
            }
            // update
            inspiration.setId(id);
            inspiration.setInspiration(inspirationParam.getInspiration());
            inspirationMapper.updateById(inspiration);
        }
        return Result.success(inspiration.getId());
    }

    @Transactional
    @Override
    public Result<List<InspirationVo>> getAllInspiration(String token) {
        Integer authorId = JWTTokenUtils.getIdByToken(token);
        LambdaQueryWrapper<Inspiration> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Inspiration::getAuthorId, authorId);
        List<Inspiration> inspirations = inspirationMapper.selectList(lambdaQueryWrapper);
        ArrayList<InspirationVo> inspirationVos = new ArrayList<>();
        for (Inspiration ins : inspirations){
            inspirationVos.add(new InspirationVo(ins.getId(), ins.getInspiration()));
        }
        return Result.success(inspirationVos);
    }

    @Transactional
    @Override
    public Result<Integer> deleteInspiration(Integer id, String token) {
        Inspiration inspiration = inspirationMapper.selectById(id);
        if(inspiration == null){
            return Result.fail(ResultEnum.NOT_CONTAIN);
        }
        if(!inspiration.getAuthorId().equals(JWTTokenUtils.getIdByToken(token))){
            return Result.fail(ResultEnum.NOT_YOUR_INS);
        }
        return Result.success(inspirationMapper.deleteById(id));
    }
}
