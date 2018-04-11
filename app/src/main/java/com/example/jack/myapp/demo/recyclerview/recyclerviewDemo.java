package com.example.jack.myapp.demo.recyclerview;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.jack.myapp.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018/4/10.
 */

public class recyclerviewDemo extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> mDatas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_demo);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        initDatas();
        final MyAdapter myAdapter = new MyAdapter(mDatas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        setItemDragAndSwipe(myAdapter);
    }

    private void setItemDragAndSwipe(final MyAdapter myAdapter) {
        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlag = ItemTouchHelper.LEFT;
                return makeMovementFlags(dragFlag,swipeFlag);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                myAdapter.onDrag(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            myAdapter.onSwipe(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 50 ; i++) {
            mDatas.add("item"+i);
        }
    }
}
