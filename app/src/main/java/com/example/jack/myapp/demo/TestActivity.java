package com.example.jack.myapp.demo;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.blankj.utilcode.util.EncodeUtils;
import com.example.jack.myapp.R;
import com.example.jack.myapp.demo.expandlistview.RvAdapter;
import com.example.jack.myapp.http.Api;
import com.example.jack.myapp.http.ApiService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestActivity extends AppCompatActivity {
    private AutoCompleteTextView actv;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_test);

        setTitle("TEST");

        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.getURL())
                .client(client)
                .build();
        Call<ResponseBody> call = retrofit.create(ApiService.class).test();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                InputStream inputStream = response.body().byteStream();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
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

    /**
     * 弹出底部弹框
     * @param view
     */
    public void onClick(View view) {
         bottomSheetDialog = new BottomSheetDialog(TestActivity.this);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);

        View contentView = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog, null);
        initContentView(contentView);

        bottomSheetDialog.setContentView(contentView);
        bottomSheetDialog.show();


    }

    private void initContentView(View contentView) {
        RecyclerView recyclerView;
        Button btnOk;
        NestedScrollView scrollView;

        recyclerView = contentView.findViewById(R.id.recyclerView);
        btnOk = contentView.findViewById(R.id.btn_Ok);
        scrollView = contentView.findViewById(R.id.bottom_sheet);

        List<String> datas = new ArrayList<>();
        for (int i = 0; i <2 ; i++) {
            datas.add("李承艳 "+i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RvAdapter(datas));

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        BottomSheetBehavior behavior = BottomSheetBehavior.from(scrollView);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.d("test", "onSlide: "+newState);
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d("test", "onSlide: "+slideOffset);
                bottomSheet.scrollBy(0, (int) slideOffset);
            }
        });
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
