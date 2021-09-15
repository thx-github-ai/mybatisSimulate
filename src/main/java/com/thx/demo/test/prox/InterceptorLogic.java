package com.thx.demo.test.prox;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * @Classname InterceptorLogic
 * @Description 拦截器
 * @Date 2021/9/13 20:14
 * @Created by thx
 */
public class InterceptorLogic implements InvocationHandler {

    private Object target = null;
    private Interceptor myInterceptor = null;
    public InterceptorLogic(Object target, Interceptor myInterceptor) {
        //绑定真实对象和拦截器
        this.target =  target;
        this.myInterceptor = myInterceptor;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (myInterceptor == null) {
            //如果没有给代理逻辑类配置一个拦截器的话，则直接反射真实对象的方法
            return method.invoke(target, args);
        }

        if (myInterceptor.before(proxy,target, method, args) == true) {
            //调用前置方法，如果前置方法返回true则可以通过反射调用真实对象的方法
            method.invoke(target, args);
        } else {
            myInterceptor.around(proxy, target, method, args);
            //如果前置方法返回false，则不能调用真实对象的方法，而是调用around方法
        }
        myInterceptor.after(proxy, target, method, args);
        //无论前置方法返回true或false，after方法在最后一定会被调用
        return null;
    }
}
