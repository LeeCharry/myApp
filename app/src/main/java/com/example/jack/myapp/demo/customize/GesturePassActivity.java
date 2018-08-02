package com.example.jack.myapp.demo.customize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jack.myapp.R;

public class GesturePassActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_pass);

       findViewById(R.id.gesture_pwd);
//        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 circleView.startPlay();
//             }
//         });
    }
}
