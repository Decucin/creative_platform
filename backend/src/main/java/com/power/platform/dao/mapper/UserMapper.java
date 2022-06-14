package com.power.platform.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.power.platform.dao.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
