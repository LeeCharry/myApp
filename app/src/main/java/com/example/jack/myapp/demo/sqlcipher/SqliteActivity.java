package com.example.jack.myapp.demo.sqlcipher;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.R;
import com.example.jack.myapp.wanandroid.BaseActivity;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.nio.BufferUnderflowException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * 加密数据库
 */
public class SqliteActivity extends AppCompatActivity implements View.OnClickListener{
    private SQLiteDatabase database;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        initView();
        SQLiteDatabase.loadLibs(this);
        MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(SqliteActivity.this, "demo.db", null, 1);
//      database = myDataBaseHelper.getWritableDatabase(AppConstant.SECRET_KEY);
        database = myDataBaseHelper.getWritableDatabase("");
    }

    private void initView() {
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
         tvData = findViewById(R.id.tv_data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                ContentValues values = new ContentValues();
                values.put("name","小王子");
                values.put("pages",521);
                values.put("author","圣埃克苏佩里");
                database.insert("Book",null,values);
                ToastUtils.showShort("添加成功");
                break;
            case R.id.btn_query:
                Cursor book = database.query("Book", null, null, null, null, null, null);
                if (book.moveToNext()) {
                    String name = book.getString(book.getColumnIndex("name"));
                    int pages = book.getInt(book.getColumnIndex("pages"));
                    String author = book.getString(book.getColumnIndex("author"));
                    tvData.setText("name : "+name+"  pages: "+pages+"  author:"+author+"\n");
                    ToastUtils.showShort("查询成功");
                }
                book.close();
                break;
        }
    }
}
