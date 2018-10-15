package com.example.jack.myapp.demo.BezierDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.example.jack.myapp.R;

/**
 * Created by lcy on 2018/8/21.
 */

public class BezierActivity extends AppCompatActivity {
    private RadioGroup rgMode;
    private ThirdBezier thidBezier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);

        thidBezier = findViewById(R.id.thidBezier);
        rgMode = findViewById(R.id.rg_mode);

        rgMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_control1:
                        thidBezier.setMode(true);

                        break;
                    case R.id.rb_control2:
                        thidBezier.setMode(false);
                        break;
                }
            }
        });
    }
}
