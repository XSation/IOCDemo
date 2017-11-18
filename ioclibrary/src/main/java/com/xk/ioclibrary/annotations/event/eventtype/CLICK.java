package com.xk.ioclibrary.annotations.event.eventtype;

import android.view.View;

import com.xk.ioclibrary.annotations.event.BaseEvent;

/**
 * Created by xuekai on 2017/11/18.
 */

@BaseEvent(setListenerMethodName = "setOnClickListener", listener = View.OnClickListener.class, callBackMethodName = "onClick")
public class CLICK extends EventType {
}