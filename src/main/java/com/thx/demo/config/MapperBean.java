package com.thx.demo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname MapperBean
 * @Description
 * @Date 2021/9/14 19:00
 * @Created by thx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapperBean {
//    接口的名字
    private String interfaceName;
//   sql 语句的集合
    private List<Function> list;
}
