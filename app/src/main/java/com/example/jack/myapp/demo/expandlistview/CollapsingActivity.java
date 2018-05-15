package com.example.jack.myapp.demo.expandlistview;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jack.myapp.R;


public class CollapsingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing);
         findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Snackbar.make(v,"this is a test!!",Snackbar.LENGTH_SHORT).show();
             }
         });
    }
}
