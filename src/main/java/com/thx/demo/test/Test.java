package com.thx.demo.test;

import com.thx.demo.mapper.UserMapper;
import com.thx.demo.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Classname Test
 * @Description 测试类
 * @Date 2021/9/11 15:38
 * @Created by thx
 */
public class Test {
    public static void main(String[] args) throws IOException {
        // 通过 io 获取到 xml 文件
        InputStream in = Resources.getResourceAsStream("Mybatis-config.xml");
//        创建 SqlSession 工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
//        通过工厂，创建会话
        SqlSession sqlSession = factory.openSession();
//        通过 SqlSession，得到 Mapper 接口
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        调用接口方法
        List<User> userList = mapper.userListAll();
//        得到输出结果
        for (User user : userList) {
            System.out.println(user.getId() + "----" + user.getUserName() + "----" + user.getPassword());
        }
    }
}
