package com.thx.demo.sqlSession;

import com.thx.demo.config.Function;
import com.thx.demo.config.MapperBean;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Classname MyMapperProxy
 * @Description
 * @Date 2021/9/14 18:32
 * @Created by thx
 */
@AllArgsConstructor
public class MyMapperProxy implements InvocationHandler {
    private MySqlSession mySqlSession;
    private MyConfiguration myConfiguration;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean mapperBean = myConfiguration.readMapper("UserMapper.xml");
//        判断是否 xml 对应的接口
        if (!method.getDeclaringClass().getName().equals(mapperBean.getInterfaceName())) {
            return null;
        }
        List<Function> list = mapperBean.getList();
        if (null != list || 0 != list.size()) {
            for (Function function : list) {
                if (judge(method, function, "userListAll")) {
                    return mySqlSession.selectOne(function.getSql());
                }
                if (judge(method, function, "deleteById")) {
                     mySqlSession.deleteOne(function.getSql(), String.valueOf(args[0]));
                }
                if (judge(method, function, "updateById")) {
                     mySqlSession.updateOne(function.getSql(), String.valueOf(args[0]));
                }
                if (judge(method, function, "insertUser")) {
                     mySqlSession.insertOne(function.getSql(), String.valueOf(args[0]));
                }

            }
        }
        return null;
    }

    private boolean judge(Method method, Function function, String methodId) {
        if (method.getName().equals(function.getFunctionName()) && function.getFunctionName().equals(methodId)) {
            return true;
        } else {
            return false;
        }
    }
}
