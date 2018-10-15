package com.example.jack.myapp.demo.head_line;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jack.myapp.R;

import java.util.List;

/**
 * Created by lcy on 2018/7/27.
 */

class MyChannelAdapter2 extends BaseAdapter {
    private List<ChannelBean> data;

    private CallBack callBack;
    public boolean deleteFlag = false;
    public int removeIndex;
    public boolean addFlag = false;
    public int tempY;
    public int tempX;
    int[] xy = new int[2];
    public Bitmap tempBitmap;
    private AppCompatActivity context;
    private ViewGroup animLayout;

    /** 添加频道列表 */
    public void addItem(String channel) {
        data.add(new ChannelBean(channel,false));
        notifyDataSetChanged();
    }

    /** 要删除的position */
    public int remove_position = -1;
    /** 设置删除的position */
    public void setRemove(int position) {
        remove_position = position;
        notifyDataSetChanged();
        // notifyDataSetChanged();
    }
    /** 删除频道列表 */
    public void remove() {
        data.remove(remove_position);
        remove_position = -1;
        notifyDataSetChanged();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    setAnim();
                    break;
            }

        }
    };
    private View finalConvertView2;
    private boolean isVisible = true;

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    private void setAnim() {
        final ImageView imageView = new ImageView(context);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            imageView.setImageBitmap(tempBitmap);
//            imageView.layout(tempX, tempY, tempX + tempBitmap.getWidth(), tempY + tempBitmap.getHeight());
//            imageView.setImageBitmap(tempBitmap);
            imageView.setImageResource(R.mipmap.ic_hot);

//            int currentColume = position % 4;
//            int currentRow = position / 4;
//            int dx = convertView.getWidth() * (tempY - currentColume);
//            int dy = 2 * convertView.getHeight() - 10 + currentRow * convertView.getHeight();


        animLayout = createAnimLayout();
        addViewToAnimlayout(animLayout,imageView,tempX,tempY);


//        final   TranslateAnimation translateAnimation  = new TranslateAnimation(Animation.ABSOLUTE, tempX, Animation.ABSOLUTE, xy[0] , Animation.ABSOLUTE, tempY, Animation.ABSOLUTE, xy[1]);
//        translateAnimation.setDuration(5000);
//        translateAnimation.setInterpolator(new AccelerateInterpolator());

        int endX = 0 - tempX + 40;// 动画位移的X坐标
        int endY = xy[1] - tempY;// 动画位移的y坐标

        TranslateAnimation translateAnimationX = new TranslateAnimation(-endX,
                0, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,
                -endY, 0);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        final AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(800);// 动画的执行时间
        imageView.startAnimation(set);

        imageView.startAnimation(set);

        finalConvertView2.setVisibility(View.INVISIBLE);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                finalConvertView2.setVisibility(View.VISIBLE);

                    imageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setVisibility(View.GONE);
                finalConvertView2.setVisibility(View.VISIBLE);
                set.cancel();
                animation.cancel();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        //置为false
        addFlag = false;
    }

    public MyChannelAdapter2(List<ChannelBean> data) {
        this.data = data;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        this.context = (AppCompatActivity) parent.getContext();
        MyChannelHolder myChannelHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_channel, null);
            myChannelHolder = new MyChannelHolder(convertView);
            convertView.setTag(myChannelHolder);
        } else {
            myChannelHolder = (MyChannelHolder) convertView.getTag();
        }

        if (deleteFlag) {
            if (position >= removeIndex) {
                convertView.setVisibility(View.INVISIBLE);
                final View finalConvertView = convertView;
                TranslateAnimation translateAnimation = null;
                if ((position + 1) % 4 == 0) {
                    translateAnimation = new TranslateAnimation(-3 * convertView.getWidth(), 0, convertView.getHeight(), 0);
                } else {
                    translateAnimation = new TranslateAnimation(convertView.getWidth(), 0, 0, 0);
                }
                translateAnimation.setDuration(200);
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        finalConvertView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                convertView.startAnimation(translateAnimation);
                //最后置为false
                if (position == data.size() - 1) {
                    deleteFlag = false;
                }
            }
        }
        //添加的动画

//        if (position == data.size() - 2) {
//
//        }

//        if (addFlag && position == data.size() - 1) {
//            convertView.getLocationInWindow(xy);
//            finalConvertView2 = convertView;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    handler.sendEmptyMessage(0);
//                }
//            }).start();
//
//        }
//

        ChannelBean channelBean = data.get(position);
        myChannelHolder.itemTv.setText(channelBean.getTitle().toString());
        if (channelBean.isToDelete()) {
            myChannelHolder.ivDelete.setVisibility(View.VISIBLE);
        } else {
            myChannelHolder.ivDelete.setVisibility(View.GONE);
        }
        if (channelBean.getTitle().equals("推荐") || channelBean.getTitle().equals("关注")) {
            myChannelHolder.ivDelete.setVisibility(View.GONE);
        }

        if (!isVisible && (position == -1 + data.size())) {
            myChannelHolder.itemTv.setText("");
              myChannelHolder.itemTv.setSelected(true);
              myChannelHolder.itemTv.setEnabled(true);
        }

        final View finalConvertView1 = convertView;
        myChannelHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.onDelete(finalConvertView1, position);
                }
            }
        });
        return convertView;
    }

    /**
     * 将Imageview添加到动画层
     * @param animLayout
     * @param imageView
     * @param tempX
     * @param tempY
     */
    private void addViewToAnimlayout(ViewGroup animLayout, ImageView imageView, int tempX, int tempY) {
        animLayout.addView(imageView);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = tempX;
        lp.rightMargin = tempY;

        imageView.setLayoutParams(lp);
    }

    /**
     * 创建动画层
     *
     * @return
     */
    private LinearLayout createAnimLayout() {
        ViewGroup decorView = (ViewGroup) context.getWindow().getDecorView();
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(lp);
        linearLayout.setId(Integer.MAX_VALUE);
        linearLayout.setBackgroundResource(android.R.color.transparent);
        decorView.addView(linearLayout);
        return linearLayout;
    }

    class MyChannelHolder {
        TextView itemTv;
        ImageView ivDelete;

        public MyChannelHolder(View view) {
            itemTv = view.findViewById(R.id.item_tv);
            ivDelete = view.findViewById(R.id.iv_delete);
        }
    }

    interface CallBack {
        void onDelete(View view, int position);
    }
}
