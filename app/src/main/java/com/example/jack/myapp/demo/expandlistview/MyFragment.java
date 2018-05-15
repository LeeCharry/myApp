package com.example.jack.myapp.demo.expandlistview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jack.myapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lcy on 2018/5/10.
 */

public class MyFragment extends Fragment {
    private String content = "";
    private int dataLength;

    public void setContent(String content) {
        this.content = content;
    }
    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_my, null);
//        TextView textView = inflate.findViewById(R.id.tv_content);
        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerView);
//        textView.setText(content);
        initRvData(recyclerView);
        return inflate;
    }
    private void initRvData(RecyclerView recyclerView) {
        List<String> datas = new ArrayList<>();

        for (int i = 0; i < dataLength ; i++) {
            datas.add("JAVA"+i);
        }
        RvAdapter rvAdapter = new RvAdapter(datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(rvAdapter);
//        recyclerView.setNestedScrollingEnabled(false);
    }
    public static MyFragment getMyFragmentInstance(String content,int dataLength) {
        MyFragment myFragment = new MyFragment();
        myFragment.setContent(content);
        myFragment.setDataLength(dataLength);
        return myFragment;
    }
}
