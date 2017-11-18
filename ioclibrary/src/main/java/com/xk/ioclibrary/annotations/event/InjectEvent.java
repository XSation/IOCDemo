package com.xk.ioclibrary.annotations.event;

import com.xk.ioclibrary.annotations.event.eventtype.EventType;

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
     * view's id
     *
     */
    int[] ids();

    /**
     * event type
     */
    Class<? extends EventType> event();
}

