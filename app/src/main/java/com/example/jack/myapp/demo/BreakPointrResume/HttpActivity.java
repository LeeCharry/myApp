package com.example.jack.myapp.demo.BreakPointrResume;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jack.myapp.R;
import com.example.jack.myapp.demo.RvItemDecoration.MyDecoration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 使用线程池下载多个apk（模拟应用市场下载应用）
 */
public class HttpActivity extends AppCompatActivity {
    //    private DownLoadButton downLoadButton;
//    private DownLoadHandler downLoadHandler;
    private final static int MESSAGE_DOWNLOADING = 0;
    private int downLoadedPrecent = 0;

    //模拟来自网络的App列表数据
    private String AppJson = "[{\"appName\":\"QQ\",\"apkUrl\":\"http://imtt.dd.qq.com/16891/2356E64D93524FB7A7DC0528DA06397D.apk\",\"apkPicUrl\":\"http://pp.myapp.com/ma_icon/0/icon_6633_1528385460/96\"},\n" +
            "{\"appName\":\"携程\",\"apkUrl\":\"http://imtt.dd.qq.com/16891/0523FFA0D2A9B0AC660719E08CD03179.apk\",\"apkPicUrl\":\"http://pp.myapp.com/ma_icon/0/icon_6240_1528168810/96\"},\n" +
            "{\"appName\":\"微信\",\"apkUrl\":\"http://imtt.dd.qq.com/16891/50CC095EFBE6059601C6FB652547D737.apk\",\"apkPicUrl\":\"http://pp.myapp.com/ma_icon/0/icon_10910_1527749764/96\"},\n" +
            "{\"appName\":\"爱奇艺\",\"apkUrl\":\"http://imtt.dd.qq.com/16891/64206C165825D5E2A4F5E163C7C17A09.apk\",\"apkPicUrl\":\"http://pp.myapp.com/ma_icon/0/icon_7720_1527834657/96\"}\n" +
            "]";

//    private DownLoadButton downLoadButton;

    public String APK_PATH;
    private RecyclerView recyclerView;
    private List<AppBean> list;
    private AppRvAdapter adapter;
    private static final int UPDATE_PROGRESS = 101;
    private static final int DOWNLOAD_COMPLETE = 102;
    private Intent downloadIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        initView();
        initRv();
        downloadIntent = new Intent(HttpActivity.this,HttpService.class);
    }


    private void initRv() {
        list = new Gson().fromJson(AppJson, new TypeToken<List<AppBean>>() {
        }.getType());
        recyclerView.setLayoutManager(new LinearLayoutManager(HttpActivity.this));
        recyclerView.addItemDecoration(new MyDecoration(HttpActivity.this, 15));
        adapter = new AppRvAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void initView() {
//        downLoadButton = findViewById(R.id.downLoadButton);
        recyclerView = findViewById(R.id.recyclerView);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

//    @MsgMode(MyMode.MAIN)
//    private void onBack(Message msg) {
//        switch (msg.what) {
//            case UPDATE_PROGRESS:
//                downLoadButton.setDownLoadProgress(msg.arg1);
//                downLoadButton.setState(DownLoadButton.STATE_DOWNLOADING);
//                break;
//            case DOWNLOAD_COMPLETE:
//                downLoadButton.setDownLoadProgress(msg.arg1);
//                downLoadButton.setState(DownLoadButton.STATE_COMPLETE);
//                break;
//        }
//    }
}
