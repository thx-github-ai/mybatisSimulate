package com.thx.demo.test.prox;

import java.lang.reflect.Proxy;

/**
 * @Classname DynamicProxy
 * @Description
 * @Date 2021/9/13 20:20
 * @Created by thx
 */
public class DynamicProxy {
    public static void main(String[] args) {
        Dilireba dilireba = new Dilireba();
        //实例化真实对象
        AgentInterceptor agentInterceptor = new AgentInterceptor();
        //实例化经纪人拦截器
        CompanyInterceptor companyInterceptor = new CompanyInterceptor();
        //实例化传媒公司拦截器
        InterceptorLogic interceptorLogic = new InterceptorLogic(dilireba, agentInterceptor);
        //把经纪人拦截器和被代理对象配置进代理逻辑类中
        Object proxy1 = Proxy.newProxyInstance(dilireba.getClass().getClassLoader(), dilireba.getClass().getInterfaces(), interceptorLogic);
        //产生了第一个代理对象proxy1
        //对第一个代理对象proxy1再进行一次动态代理
        InterceptorLogic interceptorLogic2 = new InterceptorLogic(proxy1, companyInterceptor);
        //把传媒公司拦截器和被代理对象配置进代理逻辑类中，此时的被代理对象不再是迪丽热巴了，而是proxy1了
        Object proxy2 = Proxy.newProxyInstance(proxy1.getClass().getClassLoader(), proxy1.getClass().getInterfaces(), interceptorLogic2);
        //以proxy1为真实对象，生成它的代理对象proxy2
        Advertisement finalProxyObject = (Advertisement) proxy2;
        //因为proxy1实现了dilireba的Advertisement接口，proxy2又实现了proxy1的接口
        //因此可以用接口Advertisement来接收对象
        finalProxyObject.display();
    }
}
