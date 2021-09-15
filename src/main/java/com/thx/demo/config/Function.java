package com.thx.demo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname Function
 * @Description
 * @Date 2021/9/14 19:21
 * @Created by thx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Function {
//    sql 类型: select, insert, update, delete
    private String sqlType;
//    其实是 sql 的唯一主键 id 的名字
    private String functionName;
//    sql 语句，不多说
    private String sql;
//    返回结果集，设置为 Object
    private Object resultType;
//    入参的类型，int, long, String 等
    private String parameterType;
}
