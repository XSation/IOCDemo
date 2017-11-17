package com.xk.iocdemo.utils;

import android.app.Activity;
import android.view.View;

import com.xk.iocdemo.utils.annotations.InjectContentView;
import com.xk.iocdemo.utils.annotations.InjectView;
import com.xk.iocdemo.utils.annotations.event.BaseEvent;
import com.xk.iocdemo.utils.annotations.event.EventType;
import com.xk.iocdemo.utils.annotations.event.InjectEvent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuekai on 2017/11/14.
 */

public class InjectUtil {


    public static void inject(Activity activity) {
        injectLayout(activity);
        injectView(activity);
        injectEvent(activity);
    }

    private static void injectEvent(final Activity activity) {
        try {

            Method[] methods = activity.getClass().getMethods();
            for (final Method method : methods) {

                InjectEvent injectEvent = method.getAnnotation(InjectEvent.class);
                if (injectEvent == null) {
                    continue;
                }

                //找到被InjectEvent注解的方法
                //用户定义的方法
                final Method realMethod = method;


                //获取这个方法上的注解，就可以知道是要做什么
                int[] ids = injectEvent.ids();
                for (int id : ids) {
                    View view = activity.findViewById(id);
                    EventType event = injectEvent.event();
                    BaseEvent baseEvent = EventType.class.getField(event.name()).getAnnotation(BaseEvent.class);
                    final String callBackMethodName = baseEvent.callBackMethodName();
                    String setListenerMethodName = baseEvent.setListenerMethodName();
                    Class listener = baseEvent.listener();

                    //代理这个接口（比如View.OnClickListener）
                    Object listenerProxy = Proxy.newProxyInstance(activity.getClassLoader(), new Class[]{listener}, new InvocationHandler() {
                        @Override
                        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                            Object result = null;
                            if (method.getName().equals(callBackMethodName)) {
                                result = realMethod.invoke(activity, objects);
                            }
                            return result;
                        }
                    });
                    //view设置监听的方法（比如setOnClickListener）
                    Method setListenerMethod = View.class.getMethod(setListenerMethodName, listener);
                    setListenerMethod.invoke(view, listenerProxy);


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void injectLayout(Activity activity) {
        List<Annotation> annotations = new ArrayList<>();
        getAllClassAnnotations(annotations, activity.getClass());
        for (Annotation annotation : annotations) {
            if (annotation instanceof InjectContentView) {
                activity.setContentView(((InjectContentView) annotation).value());
                break;
            }
        }
    }

    //构建一个class，保存注解和field的键值对
    static class Annotation2Field {
        Annotation annotation;
        Field field;

        public Annotation2Field(Annotation annotation, Field field) {
            this.annotation = annotation;
            this.field = field;
        }

        @Override
        public String toString() {
            return "Annotation2Field{" +
                    "annotation=" + annotation +
                    ", field=" + field +
                    '}';
        }
    }

    private static void injectView(Activity activity) {
        List<Annotation2Field> annotation2Fields = new ArrayList<>();
        getAllFieldAnnotations(annotation2Fields, activity.getClass());
        for (Annotation2Field annotation2Field : annotation2Fields) {
            if (annotation2Field.annotation instanceof InjectView) {
                View view = activity.findViewById(((InjectView) annotation2Field.annotation).value());
                annotation2Field.field.setAccessible(true);
                try {
                    annotation2Field.field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 递归查找所有的注解
     *
     * @param result
     * @param aClass
     */
    private static void getAllClassAnnotations(List<Annotation> result, Class<?> aClass) {
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            result.add(annotation);
        }
        Class<?> superclass = aClass.getSuperclass();
        if (superclass != null) {
            getAllClassAnnotations(result, superclass);
        }
    }

    /**
     * 递归查找所有field的注解
     *
     * @param result
     * @param aClass
     */
    private static void getAllFieldAnnotations(List<Annotation2Field> result, Class<?> aClass) {
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Annotation[] annotations = declaredField.getAnnotations();
            for (Annotation annotation : annotations) {
                result.add(new Annotation2Field(annotation, declaredField));
            }
        }
        Class<?> superclass = aClass.getSuperclass();
        if (superclass != null) {
            getAllFieldAnnotations(result, superclass);
        }
    }

}
