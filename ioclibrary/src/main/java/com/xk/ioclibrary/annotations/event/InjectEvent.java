package com.xk.ioclibrary.annotations.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xuekai on 2017/11/17.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectEvent {
    /**
     * view的id
     *
     * @return
     */
    int[] ids();

    /**
     * 事件类型
     * @return
     */
    EventType event();
}

