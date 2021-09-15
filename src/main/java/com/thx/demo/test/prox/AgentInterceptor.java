package com.thx.demo.test.prox;

import java.lang.reflect.Method;

/**
 * @Classname AgentInterceptor
 * @Description
 * @Date 2021/9/13 20:20
 * @Created by thx
 */
public class AgentInterceptor implements Interceptor{
    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {

        System.out.println("【经纪人before方法】经纪人正在与您谈判前期工作");
        return false;
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【经纪人around方法】经纪人before方法返回false，本方法被调用");
    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【经纪人after方法】无论经纪人before方法返回true或false，本方法都会被调用");
    }
}
