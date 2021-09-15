package com.thx.demo.bean;

import com.thx.demo.test.prox.Interceptor;
import lombok.Data;

/**
 * @Classname User
 * @Description
 * @Date 2021/9/11 15:41
 * @Created by thx
 */
@Data
public class User {
    /**
     * 都是对应数据库的 user 字段
     */
    private static final long serialVersionUID = -277386013177856279L;
    private Integer id;
    private String userName;
    private String password;

    @Override
    public String toString() {
        return id + "," + userName + "," + password;
    }
}
