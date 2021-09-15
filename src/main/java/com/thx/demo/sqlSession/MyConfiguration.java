package com.thx.demo.sqlSession;

import com.mysql.jdbc.Connection;
import com.thx.demo.config.Function;
import com.thx.demo.config.MapperBean;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Classname MyConfiguration
 * @Description
 * @Date 2021/9/14 18:32
 * @Created by thx
 */
@Slf4j
public class MyConfiguration {
    private ClassLoader loader = ClassLoader.getSystemClassLoader();

//    下面开始读取 xml 并处理
    public Connection build() {
        try {
//            读取到 xml 的配置
            InputStream stream = loader.getResourceAsStream("Mybatis-config.xml");
            SAXReader reader = new SAXReader();
            Document document = reader.read(stream);
            Element element = document.getRootElement();
            log.info("加载数据库 config.xml 成功");
//            读取成功，接下来开始连接数据库
            return evalDataSource(element);
        } catch (DocumentException e) {
            log.error("都市 config.xml 失败" + e.getMessage());
            throw new RuntimeException("读取 xml 失败");
        }
    }

    private Connection evalDataSource(Element element) {
//        对应 标签的名字
        if (!element.getName().equals("database")) {
            throw new RuntimeException("读取到了错误的信息");
        }
        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;
//      读取到 property 标签，循环，获取到 value 和 name
        for (Object item : element.elements("property")) {
            Element e = (Element) item;
            String value = getValue(e);
            String name = e.attributeValue("name");
//            获取 name 和 value，二者缺一不可
            if (name == null || value == null) {
                throw new RuntimeException("读取 property 信息失败");
            }
            switch (name) {
                case "driverClassName":
                    driverClassName = value;
                    break;
                case "url":
                    url = value;
                    break;
                case "username":
                    username = value;
                    break;
                case "password":
                    password = value;
                    break;
                default:
                    throw new RuntimeException("读取数据库配置失败");
            }
        }
        try {
//            开始连接数据库
            Class.forName(driverClassName);
            log.info("连接数据库的 driverClassName 成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
//            得到数据库连接
            connection = (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

    private String getValue(Element element) {
        return element.hasContent() ? element.getText() : element.getParent().attributeValue("value");
    }

    public MapperBean readMapper(String path) {
        MapperBean mapperBean = new MapperBean();
        try {
            InputStream stream = loader.getResourceAsStream(path);
            SAXReader reader = new SAXReader();
            Document document = reader.read(stream);
            Element root = document.getRootElement();

//            存储 mapper 节点的 nameSpace 值
            mapperBean.setInterfaceName(root.attributeValue("namespace").trim());
//            存储方法
            List<Function> list = new ArrayList<>();
            for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
                Function function = new Function();
                Element element = (Element) iterator.next();
                String sqlType = element.getName().trim();
                String functionName = element.attributeValue("id").trim();
                String sql = element.getText().trim();
                String resultType = null;
                Object newInstance = null;
                if (null != element.attributeValue("resultType")) {
                    resultType = element.attributeValue("resultType").trim();
                    try {
                        newInstance = Class.forName(resultType).newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
//                    function.setResultType(newInstance);
                    function.setResultType(resultType);
                }
                function.setSqlType(sqlType);
                function.setFunctionName(functionName);
                function.setSql(sql);

                list.add(function);

            }
            mapperBean.setList(list);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return mapperBean;
    }
}
