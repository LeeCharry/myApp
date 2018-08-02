package com.example.jack.myapp.demo.customize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jack.myapp.R;

public class CustomizeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        final CircleView circleView = findViewById(R.id.circleView);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 circleView.startPlay();
             }
         });
    }
}
