package com.example.jack.myapp.demo.RvItemDecoration;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jack.myapp.R;

import java.util.List;

/**
 * Created by lcy on 2018/6/19.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyHolder> {
    private List<String> list;

    public RvAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.tvItem.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
         TextView tvItem;
        public MyHolder(View itemView) {
            super(itemView);
            tvItem =itemView.findViewById(R.id.tv_item);
        }
    }
}
