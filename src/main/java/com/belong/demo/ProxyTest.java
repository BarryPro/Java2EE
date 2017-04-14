package com.belong.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by belong on 2017/4/7.
 */
public class ProxyTest implements  InvocationHandler{
    public static void main(String[] args) {
        Proxy proxy;
        InvocationHandler invocationHandler = new ProxyTest();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
