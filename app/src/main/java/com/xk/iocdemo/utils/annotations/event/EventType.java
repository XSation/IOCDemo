package com.xk.iocdemo.utils.annotations.event;

import android.view.View;

public enum EventType {
    @BaseEvent(setListenerMethodName = "setOnClickListener", listener = View.OnClickListener.class, callBackMethodName = "onClick")
    Click,
    @BaseEvent(setListenerMethodName = "setOnLongClickListener", listener = View.OnLongClickListener.class, callBackMethodName = "onLongClick")
    LongClick
}
