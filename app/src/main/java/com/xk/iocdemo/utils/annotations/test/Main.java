package com.xk.iocdemo.utils.annotations.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xuekai on 2017/11/17.
 */

public class Main {

    public static void main(String[] args) throws NoSuchMethodException {
        Main main = new Main();

        //想要拦截method的调用时机

        CallBack o = (CallBack) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{CallBack.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("proxy");
                return null;
            }
        });

        o.method();

    }

}


interface CallBack {
    void method();
}
