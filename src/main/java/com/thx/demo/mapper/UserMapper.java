package com.thx.demo.mapper;

import com.thx.demo.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description ceshi
 * @Date 2021/9/11 15:42
 * @Created by thx
 */
public interface UserMapper {
    List<User> userListAll();

    void insertUser(@Param("user") User user);

    void updateById(@Param("user") User user);

    void deleteById(@Param("id") Integer id);
}
