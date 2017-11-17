package com.xk.iocdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xk.iocdemo.utils.BaseActivity;
import com.xk.iocdemo.utils.annotations.InjectContentView;
import com.xk.iocdemo.utils.annotations.InjectView;
import com.xk.iocdemo.utils.annotations.event.EventType;
import com.xk.iocdemo.utils.annotations.event.InjectEvent;

@InjectContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @InjectView(R.id.b1)
    private Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity", "onCreate-->" + btn);


    }


    @InjectEvent(ids = {R.id.b1}, event = EventType.LongClick)
    public boolean lclick(View v) {
        Toast.makeText(this, "longclick" + v.getId(), Toast.LENGTH_SHORT).show();
        return false;
    }

    @InjectEvent(ids = {R.id.b2,R.id.b1}, event = EventType.Click)
    public void click(View v) {
        Toast.makeText(this, "click" + v.getId(), Toast.LENGTH_SHORT).show();
    }
}
