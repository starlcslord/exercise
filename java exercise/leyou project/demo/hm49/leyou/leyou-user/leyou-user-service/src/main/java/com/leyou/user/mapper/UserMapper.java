package com.leyou.user.mapper;

import com.leyou.user.pojo.User;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.annotation.MapperScan;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {
}
