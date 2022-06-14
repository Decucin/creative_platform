package com.power.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.power.platform.dao.mapper.UserMapper;
import com.power.platform.dao.pojo.User;
import com.power.platform.service.LoginService;
import com.power.platform.utils.JWTTokenUtils;
import com.power.platform.vo.Result;
import com.power.platform.vo.ResultEnum;
import com.power.platform.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    @Override
    public Result<String> login(LoginParam loginParam) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, loginParam.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            return Result.fail(ResultEnum.USER_NOT_EXIST);
        }
        if(!encoder.matches(loginParam.getPassword(), user.getPassword())){
            return Result.fail(ResultEnum.PASSWORD_ERROR);
        }
        // 登录成功
        String token = JWTTokenUtils.createToken(user.getId());
        // 存token到redis中
        redisTemplate.opsForValue().set("Token_" + token, JSON.toJSONString(user), 1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Transactional
    @Override
    public Result<String> logout(String token) {
        redisTemplate.delete("Token_" + token);
        return Result.success("退出账号成功！");
    }

    @Transactional
    @Override
    public Result<String> registry(LoginParam loginParam) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, loginParam.getUsername());
        if(userMapper.selectOne(queryWrapper) != null){
            return Result.fail(ResultEnum.USER_ALREADY_EXIST);
        }
        User user = new User();
        user.setUsername(loginParam.getUsername());
        // 这里把密码进行加密
        user.setPassword(encoder.encode(loginParam.getPassword()));
        int insert = userMapper.insert(user);
        if(insert == 1){
            return Result.success("注册成功！");
        }
        return Result.fail(ResultEnum.TRY_AGAIN);
    }
}
