package com.xk.iocdemo;

import android.text.TextWatcher;

import com.xk.ioclibrary.annotations.event.BaseEvent;
import com.xk.ioclibrary.annotations.event.eventtype.EventType;

/**
 * 自定义事件注解
 * Created by xuekai on 2017/11/18.
 */
@BaseEvent(setListenerMethodName = "addTextChangedListener", listener = TextWatcher.class, callBackMethodName = "beforeTextChanged")
public class EDITTEXT_BEFORETEXTCHANGED extends EventType {
}
