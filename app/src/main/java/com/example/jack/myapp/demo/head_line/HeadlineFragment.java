package com.example.jack.myapp.demo.head_line;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jack.myapp.AppConstant;
import com.example.jack.myapp.R;

/**
 * Created by lcy on 2018/7/27.
 */

public class HeadlineFragment extends Fragment {
    private TextView tvContent;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_head_line, null);
        tvContent = view.findViewById(R.id.tv_content);
        tvContent.setText(getTitle());
        return view;

    }
}
