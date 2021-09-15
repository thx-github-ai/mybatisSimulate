package com.thx.demo.sqlSession;

import com.mysql.jdbc.Connection;
import com.thx.demo.bean.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname MyExcutor
 * @Description
 * @Date 2021/9/14 18:32
 * @Created by thx
 */
@Slf4j
public class MyExecutor implements Executor {

    private MyConfiguration myConfiguration = new MyConfiguration();

    @Override
    public <T> T query(String statement) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(statement);
//            pst.setString(0, parameter.toString());
            resultSet = pst.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                users.add(user);
            }
            log.info("遍历结果集成功");
            return (T) users;
        } catch (Exception e) {
            log.error("遍历结果集失败");
            e.printStackTrace();
        } finally {
            close(resultSet, pst, connection);
        }
        return null;
    }

    /**
     * 得到数据库连接
     *
     * @return
     */
    private Connection getConnection() {
        Connection connection = myConfiguration.build();
        try {
            connection.setAutoCommit(false);
            log.info("连接数据库成功");
            return connection;
        } catch (Exception e) {
            log.error("连接数据库失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库连接
     *
     * @param resultSet
     * @param pst
     * @param connection
     */
    private void close(ResultSet resultSet, PreparedStatement pst, Connection connection) {
        try {
            if (pst != null) {
                pst.close();
                log.info("PreParedStatement 已经关闭了");
            }
            if (connection != null) {
                connection.close();
                log.info("Connection 已经关闭");
            }
        } catch (SQLException e) {
            log.error("关闭失败" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 插入方法
     *
     * @param statement
     * @param parameter
     */
    @Override
    public void save(String statement, Object parameter) {
        Connection connection = getConnection();
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(statement);
            String[] strings = parameter.toString().split(",");
            pst.setInt(1, Integer.parseInt(strings[0]));
            pst.setString(2, strings[1]);
            pst.setString(3, strings[2]);
            pst.execute();
            commit(connection);
            log.info("save 设置成功");
        } catch (SQLException e) {
            rollback(connection);
            log.error("save 设置失败" + e.getMessage());
            e.printStackTrace();
        } finally {
            close(null, pst, connection);
        }
    }

    /**
     * 修改方法
     *
     * @param statement
     * @param parameter
     */
    @Override
    public void modify(String statement, Object parameter) {
        Connection connection = getConnection();
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(statement);
            String[] strings = parameter.toString().split(",");
            pst.setString(1, strings[1]);
            pst.setString(2, strings[2]);
            pst.setInt(3, Integer.parseInt(strings[0]));
            pst.execute();
            commit(connection);
            log.info("modify 设置成功");
        } catch (SQLException e) {
            rollback(connection);
            log.error("modify 设置失败" + e.getMessage());
            e.printStackTrace();
        } finally {
            close(null, pst, connection);
        }
    }

    /**
     * 删除方法
     *
     * @param statement
     * @param parameter
     */
    @Override
    public void remove(String statement, Object parameter) {
        Connection connection = getConnection();
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(statement);
            pst.setString(1, parameter.toString());
            pst.execute();
            commit(connection);
            log.info("remove 设置成功");
        } catch (SQLException e) {
            rollback(connection);
            log.error("remove 设置失败" + e.getMessage());
            e.printStackTrace();
        } finally {
            close(null, pst, connection);
        }
    }

    private void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
