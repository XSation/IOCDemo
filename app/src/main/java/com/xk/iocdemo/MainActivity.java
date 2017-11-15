package com.xk.iocdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.xk.iocdemo.utils.BaseActivity;
import com.xk.iocdemo.utils.annotations.ContentView;
import com.xk.iocdemo.utils.annotations.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.b1)
    private Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity","onCreate-->"+btn);
    }
}
