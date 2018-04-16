package com.example.jack.myapp.demo.date;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.jack.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class DateGrideActivity extends AppCompatActivity {
    private GridView gridView;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_gride);
        gridView = (GridView) findViewById(R.id.grid_view);
        list = new ArrayList<>();
        DateGridAdapter dateGridAdapter = new DateGridAdapter(list);
        gridView.setAdapter(dateGridAdapter);
    }
}
