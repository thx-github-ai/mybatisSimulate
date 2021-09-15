package com.thx.demo.test.prox;

import java.lang.reflect.Method;

/**
 * @Classname CompanyInterceptor
 * @Description
 * @Date 2021/9/13 20:21
 * @Created by thx
 */
public class CompanyInterceptor implements Interceptor{
    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {

        System.out.println("【传媒公司before方法】传媒公司正在与您谈判前期工作");
        return true;
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【传媒公司around方法】传媒公司before方法返回false，本方法被调用");
    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【传媒公司after方法】无论传媒公司before方法返回true或false，本方法都会被调用");
    }
}
