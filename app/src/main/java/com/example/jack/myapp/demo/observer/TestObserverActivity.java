package com.example.jack.myapp.demo.observer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jack.myapp.R;

import org.greenrobot.eventbus.EventBus;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.http.Body;

/**
 * 观察者模式例子
 */

public class TestObserverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_observer);

        Teacher teacher = new Teacher();
        Student stu1 = new Student("里斯");
        Student stu2 = new Student("咱三");
        Student stu3 = new Student("王二");
        teacher.addObserver(stu1);
        teacher.addObserver(stu2);
        teacher.addObserver(stu3);

        teacher.setHomeWork("第一页第三题");
        teacher.setHomeWork("第二页第五题");

        teacher.deleteObserver(stu1);
        teacher.deleteObserver(stu2);
        teacher.setHomeWork("第五页第四题");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
