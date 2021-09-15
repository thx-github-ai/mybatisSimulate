package com.thx.demo.test.prox;

import java.lang.reflect.Method;

/**
 * @Classname MyInterceptor
 * @Description
 * @Date 2021/9/13 20:17
 * @Created by thx
 */
public class MyInterceptor implements Interceptor{

    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("before 方法");
        return false;
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("around 方法");
    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("after 方法");
    }
}
