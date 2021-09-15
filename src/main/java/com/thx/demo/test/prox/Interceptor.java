package com.thx.demo.test.prox;

import java.lang.reflect.Method;

/**
 * @Classname Interceptor
 * @Description
 * @Date 2021/9/13 20:17
 * @Created by thx
 */
public interface Interceptor {
    public boolean before(Object proxy, Object target, Method method, Object[] args);

    public void around(Object proxy, Object target, Method method,Object[] args);

    public void after(Object proxy, Object target, Method method,Object[] args);
}
