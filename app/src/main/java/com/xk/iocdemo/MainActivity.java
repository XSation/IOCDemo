package com.xk.iocdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xk.ioclibrary.BaseActivity;
import com.xk.ioclibrary.annotations.InjectContentView;
import com.xk.ioclibrary.annotations.InjectView;
import com.xk.ioclibrary.annotations.event.InjectEvent;
import com.xk.ioclibrary.annotations.event.eventtype.CLICK;
import com.xk.ioclibrary.annotations.event.eventtype.EDITTEXT_TEXT_CHANGE;
import com.xk.ioclibrary.annotations.event.eventtype.LONGCLICK;

@InjectContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @InjectView(R.id.b1)
    private Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity", "onCreate-->" + btn);

    }

    @InjectEvent(ids = {R.id.et}, event = EDITTEXT_TEXT_CHANGE.class)
    public void textChange(CharSequence charSequence, int i, int i1, int i2) {
        Toast.makeText(this, "" + charSequence + i + i1 + i2, Toast.LENGTH_SHORT).show();
    }


    @InjectEvent(ids = {R.id.b1}, event = LONGCLICK.class)
    public boolean myClick(View v) {
        Toast.makeText(this, "longclick" + v.getId(), Toast.LENGTH_SHORT).show();
        return true;
    }

    @InjectEvent(ids = {R.id.b2, R.id.b1}, event = CLICK.class)
    public void longClick(View v) {
        Toast.makeText(this, "click" + v.getId(), Toast.LENGTH_SHORT).show();
    }

    @InjectEvent(ids = {R.id.et}, event = EDITTEXT_BEFORETEXTCHANGED.class)
    public void vvv(CharSequence var1, int var2, int var3, int var4){
        Toast.makeText(this, "before", Toast.LENGTH_SHORT).show();
    }
}
