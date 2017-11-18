package com.xk.ioclibrary.annotations.event.eventtype;

import android.view.View;

import com.xk.ioclibrary.annotations.event.BaseEvent;

/**
 * Created by xuekai on 2017/11/18.
 */

@BaseEvent(setListenerMethodName = "setOnLongClickListener", listener = View.OnLongClickListener.class, callBackMethodName = "onLongClick")
public class LONGCLICK extends EventType {
//    EDITTEXT_TEXT_CHANGE
}