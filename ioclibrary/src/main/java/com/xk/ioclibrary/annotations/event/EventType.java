package com.xk.ioclibrary.annotations.event;

import android.text.TextWatcher;
import android.view.View;

public enum EventType {
    @BaseEvent(setListenerMethodName = "setOnClickListener", listener = View.OnClickListener.class, callBackMethodName = "onClick")
    Click,
    @BaseEvent(setListenerMethodName = "setOnLongClickListener", listener = View.OnLongClickListener.class, callBackMethodName = "onLongClick")
    LongClick,
    @BaseEvent(setListenerMethodName = "addTextChangedListener", listener = TextWatcher.class, callBackMethodName = "onTextChanged")
    EDITTEXT_TEXT_CHANGE
}
