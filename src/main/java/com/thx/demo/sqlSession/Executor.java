package com.thx.demo.sqlSession;

/**
 * @Classname Executor
 * @Description 执行器
 * @Date 2021/9/14 18:32
 * @Created by thx
 */
public interface Executor {
    <T> T query(String statement);

    void save(String statement, Object parameter);

    void modify(String statement, Object parameter);

    void remove(String statement, Object parameter);

}
