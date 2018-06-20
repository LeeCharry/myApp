package com.example.jack.myapp.demo.BreakPointrResume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.example.jack.myapp.R;
import com.example.jack.myapp.demo.view.DownLoadButton;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 2018/6/10.
 */

public class AppRvAdapter extends RecyclerView.Adapter<AppRvAdapter.AppHolder> {
    private List<AppBean> list;
    private Context context;
    private Intent downloadIntent;
    private static String APK_DIR = Environment.getExternalStorageDirectory() + "/Http/Apks/";
    private static final int UPDATE_PROGRESS = 101;
    private static final int DOWNLOAD_COMPLETE = 102;

    private static Map<String,DownLoadButton> downLoadButtonMap = new HashMap<>();

    public AppRvAdapter(List<AppBean> list) {
        this.list = list;

//        EventTrans.getDefault().registerObserver(this);

    }

    @NonNull
    @Override
    public AppHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        downloadIntent = new Intent(context,HttpService.class);
        return new AppHolder(LayoutInflater.from(context).inflate(R.layout.item_app,null));
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        LogUtils.a("lcy","============onDetachedFromRecyclerView===============");
        if (downloadIntent != null) {
            context.stopService(downloadIntent);
        }
//        EventTrans.getDefault().unRegisterObserver(this);

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull AppHolder holder) {
        super.onViewDetachedFromWindow(holder);
        LogUtils.a("lcy","============onViewDetachedFromWindow===============");
        if (downloadIntent != null) {
            context.stopService(downloadIntent);
        }
//        EventTrans.getDefault().unRegisterObserver(this);
    }

    @Override
    public void onBindViewHolder(@NonNull AppHolder holder, final int position) {
        AppBean appBean = list.get(position);
        Glide.with(context)
                .load(appBean.getApkPicUrl())
                .into(holder.ivPic);

        holder.tvAppName.setText(appBean.getAppName().toString());

        downLoadButtonMap.put(appBean.getApkUrl(),holder.downLoadButton);
        holder.downLoadButton.setOnDownLoadButtonClickListener(new DownLoadButton.OnDownLoadButtonClickListener() {
            @Override
            public void onClick(View v, int curState) {
                //下载apk
//                if (onDownloadingApkListener != null) {
//                    onDownloadingApkListener.onDownload(v,curState,position);
//                }
                DownLoadButton downLoadButton = (DownLoadButton) v;
                switch (curState) {
                    case DownLoadButton.STATE_NORMAL:
                        downLoadButton.setState(DownLoadButton.STATE_NORMAL);
                        downloadIntent.putExtra("apk_name",list.get(position).getAppName());
                        downloadIntent.putExtra("apk_url",list.get(position).getApkUrl());
                        context.startService(downloadIntent);
                        break;
                    case DownLoadButton.STATE_COMPLETE:
                        //安装应用
                        installApk(position);
                        break;
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class AppHolder extends RecyclerView.ViewHolder{
       ImageView ivPic;
       TextView tvAppName;
       DownLoadButton downLoadButton;
        public AppHolder(View itemView) {
            super(itemView);
            ivPic = itemView.findViewById(R.id.iv_pic);
            tvAppName = itemView.findViewById(R.id.tv_app_name);
            downLoadButton = itemView.findViewById(R.id.downLoadButton);
        }
    }

    private void installApk(int position) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);

        String APK_PATH = APK_DIR + list.get(position).getAppName()+".apk";
        intent.setDataAndType(Uri.fromFile(new File(APK_PATH)), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 广播,更新item布局
     */
    static class MyBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(HttpService.ACTION)){
                Message msg = intent.getParcelableExtra("msg");
                Bundle data = msg.getData();
                DownLoadButton downLoadButton = downLoadButtonMap.get(data.getString("apk_url"));

                switch (msg.what) {
                    case UPDATE_PROGRESS:
                        downLoadButton.setDownLoadProgress(msg.arg1);
                        downLoadButton.setState(DownLoadButton.STATE_DOWNLOADING);
                        break;
                    case DOWNLOAD_COMPLETE:
                        downLoadButton.setDownLoadProgress(msg.arg1);
                        downLoadButton.setState(DownLoadButton.STATE_COMPLETE);
                        break;
                }
            }
        }
    }
}
