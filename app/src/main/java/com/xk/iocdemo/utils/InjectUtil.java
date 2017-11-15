package com.xk.iocdemo.utils;

import android.app.Activity;
import android.view.View;

import com.xk.iocdemo.utils.annotations.ContentView;
import com.xk.iocdemo.utils.annotations.ViewInject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuekai on 2017/11/14.
 */

public class InjectUtil {
    public static void inject(Activity activity) {
        injectLayout(activity);
        injectView(activity);

    }

    private static void injectLayout(Activity activity) {
        List<Annotation> annotations = new ArrayList<>();
        getAllClassAnnotations(annotations, activity.getClass());
        for (Annotation annotation : annotations) {
            if (annotation instanceof ContentView) {
                activity.setContentView(((ContentView) annotation).value());
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
            if (annotation2Field.annotation instanceof ViewInject) {
                View view = activity.findViewById(((ViewInject) annotation2Field.annotation).value());
                annotation2Field.field.setAccessible(true);
                try {
                    annotation2Field.field.set(activity,view);
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
