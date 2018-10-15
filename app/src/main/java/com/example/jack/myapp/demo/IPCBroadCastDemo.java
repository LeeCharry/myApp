package com.example.jack.myapp.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jack.myapp.R;

/**
 * Created by lcy on 2018/9/19.
 */

public class IPCBroadCastDemo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_broad);
        findViewById(R.id.btn_releas_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("acc.tulip.com.myapplication.IPCReceiver");
                intent.putExtra("key","lichengyan");
                sendBroadcast(intent);
            }
        });
    }
}
