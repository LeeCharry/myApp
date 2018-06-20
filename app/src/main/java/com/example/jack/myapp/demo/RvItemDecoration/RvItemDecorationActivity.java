package com.example.jack.myapp.demo.RvItemDecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jack.myapp.R;
import com.example.jack.myapp.demo.recyclerview.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class RvItemDecorationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_item_decoration);
        initView();
        initData(recyclerView);
    }

    private void initData(RecyclerView recyclerView) {
        list = new ArrayList<>();
        for (int i = 0; i < 20 ; i++) {
            list.add("测试ItemDecoration "+i);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(RvItemDecorationActivity.this, 3);
       recyclerView.setLayoutManager(layoutManager);
        MyAdapter myAdapter = new MyAdapter(list);

        recyclerView.addItemDecoration(new MyDecoration(this,10));
        recyclerView.setAdapter(myAdapter);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);

    }
}
