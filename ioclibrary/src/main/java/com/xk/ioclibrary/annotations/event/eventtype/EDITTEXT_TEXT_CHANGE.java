package com.xk.ioclibrary.annotations.event.eventtype;

import android.text.TextWatcher;

import com.xk.ioclibrary.annotations.event.BaseEvent;

/**
 * Created by xuekai on 2017/11/18.
 */
@BaseEvent(setListenerMethodName = "addTextChangedListener", listener = TextWatcher.class, callBackMethodName = "onTextChanged")
public class EDITTEXT_TEXT_CHANGE extends EventType {
}