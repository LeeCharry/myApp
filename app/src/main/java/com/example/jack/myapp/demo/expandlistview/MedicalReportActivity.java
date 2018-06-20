package com.example.jack.myapp.demo.expandlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.example.jack.myapp.R;
import com.example.tulib.util.utils.DeviceUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用expandListsview实现多布局
 */
public class MedicalReportActivity extends AppCompatActivity {
    private ExpandableListView expandedLv;
    private ExpandAdapter2 expandAdapter;
    private List<GroupBean.DataBean> dataBeanList;
    private LinearLayout llParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_report);
        expandedLv = findViewById(R.id.expanded_lv);
        llParent = findViewById(R.id.ll_parent);
        DeviceUtil.setImmersiveStatus(this);
        dataBeanList = new ArrayList<>();
        for (int i = 0; i < 2 ; i++) {
            dataBeanList.add(new GroupBean.DataBean());
        }
        expandAdapter = new ExpandAdapter2(dataBeanList);
        expandedLv.setAdapter(expandAdapter);
        expandedLv.setGroupIndicator(null);
        expandedLv.setChildIndicator(null);
        for (int i = 0; i < dataBeanList.size() ; i++) {
            expandedLv.expandGroup(i);
        }
    }
}
