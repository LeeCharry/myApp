package com.example.jack.myapp.demo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.example.jack.myapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TestActivity extends AppCompatActivity {
    private AutoCompleteTextView actv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);

    }

    private String getMd5(String path) {
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            String s = bigInt.toString(16);
            Log.d("lcy", "getMd5: "+s);
//            System.out.println("文件md5值：" + s);
            return s;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

//        actv = findViewById(R.id.actv);
//        String[] letterStr ={"aa","bb","cc","dd","ee","ff","gg"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TestActivity.this,android.R.layout.simple_spinner_dropdown_item,letterStr);
//        actv.setAdapter(adapter);
//        actv.setCompletionHint("请输入首字母");
//        actv.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                ToastUtils.showShort("dismiss");
//            }
//        });
//        actv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                ToastUtils.showShort("onItemSelected    "+position);
//
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                ToastUtils.showShort("onNothingSelected    ");
//            }
//        });
//        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ToastUtils.showShort("onItemClick  "+position);
//            }
//        });
//        actv.setCompletionHint("请输入首字母");
////        actv.setCompletionHint("请输入首字母");
//    }
}
