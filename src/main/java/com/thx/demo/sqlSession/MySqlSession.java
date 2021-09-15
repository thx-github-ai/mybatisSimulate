package com.thx.demo.sqlSession;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * @Classname MySqlSession
 * @Description
 * @Date 2021/9/14 18:33
 * @Created by thx
 */
@Slf4j
public class MySqlSession {
    private Executor executor = new MyExecutor();

    private MyConfiguration myConfiguration = new MyConfiguration();

    public <T> Object selectOne(String statement) {
        log.info("我是查询的 SqlSession");
        return executor.query(statement);
    }
    public void insertOne(String statement, Object parameter) {
        log.info("我是添加的 SqlSession");
        executor.save(statement, parameter);
    }
    public void updateOne(String statement, Object parameter) {
        log.info("我是修改的 SqlSession");
        executor.modify(statement, parameter);
    }
    public void deleteOne(String statement, Object parameter) {
        log.info("我是删除的 SqlSession");
        executor.remove(statement, parameter);
    }

    public <T> T getMapper(Class<T> tClass) {
        log.info("已经获得代理对象");
        return (T) Proxy.newProxyInstance(
                tClass.getClassLoader(),
                new Class[]{tClass},
                new MyMapperProxy(this, myConfiguration)
        );
    }
}
