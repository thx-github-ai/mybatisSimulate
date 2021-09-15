package com.thx.demo.test;

import com.thx.demo.bean.User;
import com.thx.demo.mapper.UserMapper;
import com.thx.demo.sqlSession.MySqlSession;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Classname RunOwnMybatis
 * @Description
 * @Date 2021/9/14 20:01
 * @Created by thx
 */
@Slf4j
public class RunOwnMybatis {
    public static void main(String[] args) {
        MySqlSession mySqlSession = new MySqlSession();
        UserMapper mapper = mySqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.userListAll();
        for (User user : users) {
            log.info("查询内容：" + user);
        }


//        插入
//        User user = new User();
//        user.setId(5);
//        user.setUserName("admin1");
//        user.setPassword("122121");
//        mapper.insertUser(user);



//        删除
//        mapper.deleteById(5);




////        修改
//        User user1 = new User();
//        user1.setId(1);
//        user1.setUserName("修改1");
//        user1.setPassword("122121");
//        mapper.updateById(user1);
    }
}
