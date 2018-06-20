package com.example.jack.myapp.demo.MRecorder;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;

import java.io.File;
import java.io.IOException;

public class MRecorderActivity extends AppCompatActivity implements View.OnClickListener{

    private MediaRecorder recorder;
    private MediaPlayer player;
    private String recorderPath;
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrecorder);
        initView();
        initData();
    }

    private void initData() {
        recorderPath = Environment.getExternalStorageDirectory()+"/MRecorder/Voice/"+System.currentTimeMillis()+".mp3";
    }

    private void initView() {
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_end).setOnClickListener(this);
        findViewById(R.id.btn_play).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                releaseRecorder();
                startRecord();
                break;
            case R.id.btn_end:
                endRecord();
                break;
            case R.id.btn_play:
                playRecorder();
                break;
        }
    }
    /**
     * 播放录音
     */
    private void playRecorder() {
        try {
            if (player != null && player.isPlaying()){
                return;
            }
            if (player == null) {
                player = new MediaPlayer();
                player.setDataSource(recorderPath);
                //配置音量,中等音量
                player.setVolume(1,1);
                //播放是否循环
                player.setLooping(false);
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                       stopPlay();
                    }
                });
                player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        stopPlay();
                        ToastUtils.showShort("播放失败");
                        return true;
                    }
                });
            }

            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
            stopPlay();
        }
    }

    /**
     * 结束播放
     */
    private void stopPlay() {
        player.stop();
        player.release();
        player = null;
    }

    /**
     * 释放录音
     */
    private void releaseRecorder() {
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
    }

    private void endRecord() {
        recorder.stop();
        long seconds = System.currentTimeMillis() - startTime;
        if (seconds <1000){
            ToastUtils.showShort("录音时间太短！");
            return;
        }else{
            ToastUtils.showShort("录制成功");
        }
        releaseRecorder();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseRecorder();
        stopPlay();
    }
    /**
     * 开始录音
     */
    private void startRecord() {
        try {
            File outputFile = new File(recorderPath);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            outputFile.createNewFile();
            if (null == recorder) {
                recorder = new MediaRecorder();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                //设置录音文件输出位置
                recorder.setOutputFile(outputFile.getAbsolutePath());
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            }else{
                recorder.release();
            }
            //开始录音
            recorder.prepare();
            recorder.start();
            startTime = System.currentTimeMillis();
            ToastUtils.showShort("开始录音...");
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort("录音失败，请重试:"+e.getMessage().toString());
        }
    }
}
