package com.example.jack.myapp.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;

public class TestActivity extends AppCompatActivity {
    private AutoCompleteTextView actv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        actv = findViewById(R.id.actv);
        String[] letterStr ={"aa","bb","cc","dd","ee","ff","gg"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TestActivity.this,android.R.layout.simple_spinner_dropdown_item,letterStr);
        actv.setAdapter(adapter);
        actv.setCompletionHint("请输入首字母");
        actv.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
            @Override
            public void onDismiss() {
                ToastUtils.showShort("dismiss");
            }
        });
        actv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showShort("onItemSelected    "+position);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ToastUtils.showShort("onNothingSelected    ");
            }
        });
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showShort("onItemClick  "+position);
            }
        });
        actv.setCompletionHint("请输入首字母");
//        actv.setCompletionHint("请输入首字母");
    }
}
