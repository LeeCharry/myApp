package com.example.jack.myapp.demo.filedemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.example.jack.myapp.R;
import com.example.tulib.util.kit.Kits;

import java.io.File;

public class FileDemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_demo);
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            File sdDir = Environment.getExternalStorageDirectory();
            String imagePath = sdDir+ File.separator+"img/temp/";
            File file = new File(imagePath);
            if (file.exists()){
                file.mkdirs();
            }
        }
        String s = getCacheDir() + "/image/temp";
        LogUtils.a("lcy","内存路径："+s);
        File file = new File(s);
        if (file.exists()) {
            file.mkdirs();
        }
        LogUtils.a("lcy","sd卡状态："+Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED);
    }
}
