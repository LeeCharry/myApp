package com.example.jack.myapp.demo.head_line;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jack.myapp.R;
import com.example.jack.myapp.demo.head_line.helper.OnItemMoveListener;

import java.util.List;

/**
 * Created by lcy on 2018/8/17.
 * 终极适配器（欲哭无泪版）
 */

public class UitlmateChannelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemMoveListener{

    public static final int MY_CHANNEL_HEAD_TYPE = 0;
    public static final int MY_CHANNEL_TYPE = 1;
    public static final int OTHER_CHANNEL_HEAD_TYPE = 2;
    public static final int OTHER_CHANNEL_TYPE = 3;
    private int MY_CHANNEL_HEAD_TYPE_COUNT = 1;
    private int OTHER_CHANNEL_HEAD_TYPE_COUNT =  1;
    private List<ChannelBean> myChannelList;
    private List<ChannelBean> otherChannelList;
    private LayoutInflater mInflater;
    private AppCompatActivity context;
    private boolean isEditAll = false; //是否显示编辑
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ImageView mirrorView;
    private ViewGroup parent;

    public UitlmateChannelAdapter(List<ChannelBean> myChannelList, List<ChannelBean> otherChannelList, Context context) {
        this.myChannelList = myChannelList;
        this.otherChannelList = otherChannelList;
        this.mInflater = LayoutInflater.from(context);
        this.context = (AppCompatActivity) context;
        this.adapter = this;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return MY_CHANNEL_HEAD_TYPE;
        } else if (position > 0 && position < myChannelList.size() + MY_CHANNEL_HEAD_TYPE_COUNT) {
            return MY_CHANNEL_TYPE;
        } else if (position == myChannelList.size() + MY_CHANNEL_HEAD_TYPE_COUNT) {
            return OTHER_CHANNEL_HEAD_TYPE;
        } else {
            return OTHER_CHANNEL_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        recyclerView = (RecyclerView) parent;
        RecyclerView.ViewHolder holder = null;
        View itemView = null;
        switch (viewType) {
            case MY_CHANNEL_HEAD_TYPE:
                itemView = mInflater.inflate(R.layout.item_my_channel_head, parent, false);
                holder = new MyChannelHeadHolder(itemView);
                break;
            case MY_CHANNEL_TYPE:
                itemView = mInflater.inflate(R.layout.item_my_channel, parent, false);
                holder = new MyChannelHolder(itemView);
                break;
            case OTHER_CHANNEL_HEAD_TYPE:
                itemView = mInflater.inflate(R.layout.item_other_channel_head, parent, false);
                holder = new OtherHeadHolder(itemView);
                break;
            case OTHER_CHANNEL_TYPE:
                itemView = mInflater.inflate(R.layout.item_recommend_channel, parent, false);
                holder = new OtherHolder(itemView);
                break;
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType()) {
            case MY_CHANNEL_HEAD_TYPE:
                final MyChannelHeadHolder holder1 = (MyChannelHeadHolder) holder;
                //点击编辑
                holder1.tvEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isEditAll = showDeleteImag(isEditAll, holder1.tvEdit);
                        adapter.notifyDataSetChanged();  //刷新数据
                    }
                });
                break;
            case MY_CHANNEL_TYPE:
                final MyChannelHolder myChannelHolder = (MyChannelHolder) holder;
                final String myChannel = myChannelList.get(position - MY_CHANNEL_HEAD_TYPE_COUNT).getTitle().toString();
                myChannelHolder.itemTv.setText(myChannel);
                if (myChannelList.get(position - MY_CHANNEL_HEAD_TYPE_COUNT).isToDelete()) {
                    myChannelHolder.ivDelete.setVisibility(View.VISIBLE);
                } else {
                    myChannelHolder.ivDelete.setVisibility(View.GONE);
                }
                myChannelHolder.itemTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (isEditAll) {
//                            GridLayoutManager gridManager = (GridLayoutManager) recyclerView.getLayoutManager();
//                            View currentView = gridManager.findViewByPosition(position);
//                            View targetView = gridManager.findViewByPosition(myChannelList.size() + MY_CHANNEL_HEAD_TYPE_COUNT + OTHER_CHANNEL_HEAD_TYPE_COUNT);
//
//                            if (recyclerView.indexOfChild(targetView) >= 0) {
//                                int targetX, targetY;
////                            if (position % gridManager.getSpanCount() == 0) {
//                                if ((myChannelList.size() - MY_CHANNEL_HEAD_TYPE_COUNT) % gridManager.getSpanCount() == 0) {
//                                    targetView = gridManager.findViewByPosition(myChannelList.size() + MY_CHANNEL_HEAD_TYPE_COUNT + OTHER_CHANNEL_HEAD_TYPE_COUNT - 1);
//                                }
//                                targetX = targetView.getLeft();
//                                targetY = targetView.getTop();
//
//                                //先移动两个item，再进行平移动画
//                                moveMyToOther(myChannel, position);
//                                startAnim(recyclerView, currentView, targetX, targetY);
//                            }else{
//                                //先移动两个item，再进行平移动画
//                                moveMyToOther(myChannel, position);
//                            }
//                        } else {
//                            //跳转到对应的fragment
//                            ToastUtils.showShort(String.valueOf(position));
//                        }

                        int position = myChannelHolder.getAdapterPosition();
                        if (isEditAll) {
                            RecyclerView recyclerView = ((RecyclerView) parent);
                            View targetView = recyclerView.getLayoutManager().findViewByPosition(myChannelList.size() + OTHER_CHANNEL_HEAD_TYPE_COUNT+MY_CHANNEL_HEAD_TYPE_COUNT);
                            View currentView = recyclerView.getLayoutManager().findViewByPosition(position);
                            // 如果targetView不在屏幕内,则indexOfChild为-1  此时不需要添加动画,因为此时notifyItemMoved自带一个向目标移动的动画
                            // 如果在屏幕内,则添加一个位移动画
                            if (recyclerView.indexOfChild(targetView) >= 0) {
                                int targetX, targetY;

                                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                                int spanCount = ((GridLayoutManager) manager).getSpanCount();

                                // 移动后 高度将变化 (我的频道Grid 最后一个item在新的一行第一个)
                                if ((myChannelList.size() - MY_CHANNEL_HEAD_TYPE_COUNT) % spanCount == 0) {
                                    View preTargetView = recyclerView.getLayoutManager().findViewByPosition(myChannelList.size() + OTHER_CHANNEL_HEAD_TYPE_COUNT+MY_CHANNEL_HEAD_TYPE_COUNT - 1);
                                    targetX = preTargetView.getLeft();
                                    targetY = preTargetView.getTop();
                                } else {
                                    targetX = targetView.getLeft();
                                    targetY = targetView.getTop();
                                }
                                moveMyToOther(myChannelHolder);
                                startAnimation(recyclerView, currentView, targetX, targetY);

                            } else {
                                moveMyToOther(myChannelHolder);
                            }
                        } else {
//                            mChannelItemClickListener.onItemClick(v, position - MY_CHANNEL_HEAD_TYPE_COUNT);
                        }
                    }
                });
                break;
            case OTHER_CHANNEL_TYPE:
                final OtherHolder otherHolder = (OtherHolder) holder;
                final String title = otherChannelList.get(position - (myChannelList.size() + MY_CHANNEL_HEAD_TYPE_COUNT + OTHER_CHANNEL_HEAD_TYPE_COUNT)).getTitle();
                otherHolder.itemTv.setText(title);


                otherHolder.itemTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecyclerView recyclerView = ((RecyclerView) parent);
                        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                        int currentPiosition = otherHolder.getAdapterPosition();
                        // 如果RecyclerView滑动到底部,移动的目标位置的y轴 - height
                        View currentView = manager.findViewByPosition(currentPiosition);
                        // 目标位置的前一个item  即当前MyChannel的最后一个
                        View preTargetView = manager.findViewByPosition(myChannelList.size() - 1 + MY_CHANNEL_HEAD_TYPE_COUNT);

                        // 如果targetView不在屏幕内,则为-1  此时不需要添加动画,因为此时notifyItemMoved自带一个向目标移动的动画
                        // 如果在屏幕内,则添加一个位移动画
                        if (recyclerView.indexOfChild(preTargetView) >= 0) {
                            int targetX = preTargetView.getLeft();
                            int targetY = preTargetView.getTop();

                            int targetPosition = myChannelList.size() - 1 + OTHER_CHANNEL_HEAD_TYPE_COUNT+MY_CHANNEL_HEAD_TYPE_COUNT;

                            GridLayoutManager gridLayoutManager = ((GridLayoutManager) manager);
                            int spanCount = gridLayoutManager.getSpanCount();
                            // target 在最后一行第一个
                            if ((targetPosition - MY_CHANNEL_HEAD_TYPE_COUNT) % spanCount == 0) {
                                View targetView = manager.findViewByPosition(targetPosition);
                                targetX = targetView.getLeft();
                                targetY = targetView.getTop();
                            } else {
//                                targetX += preTargetView.getWidth() ;
                                targetX += preTargetView.getWidth() + (parent.getWidth()-4*preTargetView.getWidth())/4;

                                // 最后一个item可见
                                if (gridLayoutManager.findLastVisibleItemPosition() == getItemCount() - 1) {
                                    // 最后的item在最后一行第一个位置
                                    if ((getItemCount() - 1 - myChannelList.size() - OTHER_CHANNEL_HEAD_TYPE_COUNT-MY_CHANNEL_HEAD_TYPE_COUNT) % spanCount == 0) {
                                        // RecyclerView实际高度 > 屏幕高度 && RecyclerView实际高度 < 屏幕高度 + item.height
                                        int firstVisiblePostion = gridLayoutManager.findFirstVisibleItemPosition();
                                        if (firstVisiblePostion == 0) {
                                            // FirstCompletelyVisibleItemPosition == 0 即 内容不满一屏幕 , targetY值不需要变化
                                            // // FirstCompletelyVisibleItemPosition != 0 即 内容满一屏幕 并且 可滑动 , targetY值 + firstItem.getTop
                                            if (gridLayoutManager.findFirstCompletelyVisibleItemPosition() != 0) {
                                                int offset = (-recyclerView.getChildAt(0).getTop()) - recyclerView.getPaddingTop();
                                                targetY += offset;
                                            }
                                        } else { // 在这种情况下 并且 RecyclerView高度变化时(即可见第一个item的 position != 0),
                                            // 移动后, targetY值  + 一个item的高度
                                            targetY += preTargetView.getHeight();
                                        }
                                    }
                                } else {
                                    System.out.println("current--No");
                                }
                            }

                            // 如果当前位置是otherChannel可见的最后一个
                            // 并且 当前位置不在grid的第一个位置
                            // 并且 目标位置不在grid的第一个位置

                            // 则 需要延迟250秒 notifyItemMove , 这是因为这种情况 , 并不触发ItemAnimator , 会直接刷新界面
                            // 导致我们的位移动画刚开始,就已经notify完毕,引起不同步问题
                            if (currentPiosition == gridLayoutManager.findLastVisibleItemPosition()
                                    && (currentPiosition - myChannelList.size() - OTHER_CHANNEL_HEAD_TYPE_COUNT-MY_CHANNEL_HEAD_TYPE_COUNT) % spanCount != 0
                                    && (targetPosition - MY_CHANNEL_HEAD_TYPE_COUNT) % spanCount != 0) {
                                moveOtherToMyWithDelay(otherHolder);
                            } else {
                                moveOtherToMy(otherHolder);
                            }
                            startAnimation(recyclerView, currentView, targetX, targetY);

                        } else {
                            moveOtherToMy(otherHolder);
                        }

                    }
                });
                break;
        }
    }

    /**
     * 移动平移动画
     *
     * @param recyclerView
     * @param currentView
     * @param targetX
     * @param targetY
     */
    private void startAnim(RecyclerView recyclerView, final View currentView, int targetX, int targetY) {
        //创建一个镜像view
        //添加到现有viewgroup中
        //开始平移动画
        final ViewGroup parent = (ViewGroup) recyclerView.getParent();
        final ImageView mirrorView = getMirrorView(currentView);
       TranslateAnimation animation = getTranslateAnimation(currentView,targetX,targetY);
       currentView.setVisibility(View.INVISIBLE);
        mirrorView.startAnimation(animation);
       animation.setAnimationListener(new Animation.AnimationListener() {
           @Override
           public void onAnimationStart(Animation animation) {
           }
           @Override
           public void onAnimationEnd(Animation animation) {
               parent.removeView(mirrorView);
               currentView.setVisibility(View.VISIBLE);
               animation.cancel();
           }

           @Override
           public void onAnimationRepeat(Animation animation) {

           }
       });
    }

    /**
     * 获取平移动画对象
     * @param currentView
     * @param targetX
     * @param targetY
     * @return
     */
    private TranslateAnimation getTranslateAnimation(View currentView, int targetX, int targetY) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, targetX - currentView.getLeft(), Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, targetY - currentView.getTop());
        translateAnimation.setDuration(360L);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new LinearInterpolator());
        return translateAnimation;
    }

    //创建一个镜像view
    private ImageView getMirrorView(View currentView) {
        currentView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(currentView.getDrawingCache());
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(bitmap);
        currentView.setDrawingCacheEnabled(false);

        //添加到现有viewgroup中
        ViewGroup viewGroup = (ViewGroup) recyclerView.getParent();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(bitmap.getWidth(), bitmap.getHeight());
        int[] currentViewXY = new int[2];
        currentView.getLocationOnScreen(currentViewXY);

        int[] rvXY = new int[2];
        recyclerView.getLocationOnScreen(rvXY);
        layoutParams.setMargins(currentViewXY[0],currentViewXY[1]-rvXY[1],0,0);
        viewGroup.addView(imageView);
        return imageView;
    }

    private void moveMyToOther(String myChannel, int position) {
        if (position > myChannelList.size() - 1) {
            return;
        }
        otherChannelList.add(0, new ChannelBean(myChannel, false));
        myChannelList.remove(position - MY_CHANNEL_HEAD_TYPE_COUNT);
//        notifyDataSetChanged();
        notifyItemMoved(position, myChannelList.size() + MY_CHANNEL_HEAD_TYPE_COUNT + OTHER_CHANNEL_HEAD_TYPE_COUNT);
    }

    /**
     * 我的频道 移动到 其他频道
     *
     * @param myHolder
     */
    private void moveMyToOther(MyChannelHolder myHolder) {
        int position = myHolder.getAdapterPosition();

        int startPosition = position - MY_CHANNEL_HEAD_TYPE_COUNT;
        if (startPosition > myChannelList.size() - 1) {
            return;
        }
        ChannelBean item = myChannelList.get(startPosition);
        myChannelList.remove(startPosition);
        otherChannelList.add(0, item);

        notifyItemMoved(position, myChannelList.size() + OTHER_CHANNEL_HEAD_TYPE_COUNT+MY_CHANNEL_HEAD_TYPE_COUNT);
    }

    /**
     * 其他频道 移动到 我的频道
     *
     * @param otherHolder
     */
    private void moveOtherToMy(OtherHolder otherHolder) {
        int position = processItemRemoveAdd(otherHolder);
        if (position == -1) {
            return;
        }
        notifyItemMoved(position, myChannelList.size() - 1 + MY_CHANNEL_HEAD_TYPE_COUNT);
    }

    /**
     * 其他频道 移动到 我的频道 伴随延迟
     *
     * @param otherHolder
     */
    private void moveOtherToMyWithDelay(OtherHolder otherHolder) {
        final int position = processItemRemoveAdd(otherHolder);
        if (position == -1) {
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyItemMoved(position, myChannelList.size() - 1 + MY_CHANNEL_HEAD_TYPE_COUNT);
            }
        }, 360L);
    }

    /**
     * 开始增删动画
     */
    private void startAnimation(RecyclerView recyclerView, final View currentView, float targetX, float targetY) {
        final ViewGroup viewGroup = (ViewGroup) recyclerView.getParent();
        final ImageView mirrorView = addMirrorView(viewGroup, recyclerView, currentView);


        Animation animation = getTranslateAnimator(
                targetX - currentView.getLeft(), targetY - currentView.getTop());
        currentView.setVisibility(View.INVISIBLE);
        mirrorView.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewGroup.removeView(mirrorView);
                if (currentView.getVisibility() == View.INVISIBLE) {
                    currentView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 获取位移动画
     */
    private TranslateAnimation getTranslateAnimator(float targetX, float targetY) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.ABSOLUTE, targetX,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.ABSOLUTE, targetY);
        // RecyclerView默认移动动画250ms 这里设置360ms 是为了防止在位移动画结束后 remove(view)过早 导致闪烁
        translateAnimation.setDuration(360L);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

    /**
     * 添加需要移动的 镜像View
     */
    private ImageView addMirrorView(ViewGroup parent, RecyclerView recyclerView, View view) {
        /**
         * 我们要获取cache首先要通过setDrawingCacheEnable方法开启cache，然后再调用getDrawingCache方法就可以获得view的cache图片了。
         buildDrawingCache方法可以不用调用，因为调用getDrawingCache方法时，若果cache没有建立，系统会自动调用buildDrawingCache方法生成cache。
         若想更新cache, 必须要调用destoryDrawingCache方法把旧的cache销毁，才能建立新的。
         当调用setDrawingCacheEnabled方法设置为false, 系统也会自动把原来的cache销毁。
         */
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        final ImageView mirrorView = new ImageView(recyclerView.getContext());
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        mirrorView.setImageBitmap(bitmap);
        view.setDrawingCacheEnabled(false);
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);
        int[] parenLocations = new int[2];
        recyclerView.getLocationOnScreen(parenLocations);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(bitmap.getWidth(), bitmap.getHeight());
        params.setMargins(locations[0], locations[1] - parenLocations[1], 0, 0);
        parent.addView(mirrorView, params);
        //recyclerview的父布局必须是FramLayout,不能是LinearLayout,linearlayout的方向在xml中设置的是vertical，，addview的话就把镜像view add到recyclerview中了。

        return mirrorView;
    }


    private int processItemRemoveAdd(OtherHolder otherHolder) {
        int position = otherHolder.getAdapterPosition();

        int startPosition = position - myChannelList.size() - OTHER_CHANNEL_HEAD_TYPE_COUNT-MY_CHANNEL_HEAD_TYPE_COUNT;
        if (startPosition > otherChannelList.size() - 1) {
            return -1;
        }
        ChannelBean item = otherChannelList.get(startPosition);
        otherChannelList.remove(startPosition);
        myChannelList.add(item);
        return position;
    }

    private boolean showDeleteImag(boolean isEditAll, TextView tvEdit) {
        if (!isEditAll) {
            for (int i = 0; i < myChannelList.size(); i++) {
                myChannelList.get(i).setToDelete(true);
            }
            isEditAll = true;
            tvEdit.setText("完成");
        } else {
            for (int i = 0; i < myChannelList.size(); i++) {
                myChannelList.get(i).setToDelete(false);
            }
            isEditAll = false;
            tvEdit.setText("编辑");
        }
        return isEditAll;
    }

    @Override
    public int getItemCount() {
        return myChannelList.size() + otherChannelList.size() + MY_CHANNEL_HEAD_TYPE_COUNT + OTHER_CHANNEL_HEAD_TYPE_COUNT;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        ChannelBean item = myChannelList.get(fromPosition - MY_CHANNEL_HEAD_TYPE_COUNT);
        myChannelList.remove(fromPosition - MY_CHANNEL_HEAD_TYPE_COUNT);
        myChannelList.add(toPosition - MY_CHANNEL_HEAD_TYPE_COUNT, item);
        notifyItemMoved(fromPosition, toPosition);
    }

    class MyChannelHeadHolder extends RecyclerView.ViewHolder {
        TextView tvEdit;

        public MyChannelHeadHolder(View itemView) {
            super(itemView);
            tvEdit = itemView.findViewById(R.id.tv_edit);
        }
    }

    class MyChannelHolder extends RecyclerView.ViewHolder {
        TextView itemTv;
        ImageView ivDelete;

        public MyChannelHolder(View itemView) {
            super(itemView);
            itemTv = itemView.findViewById(R.id.item_tv);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
    }

    class OtherHeadHolder extends RecyclerView.ViewHolder {

        public OtherHeadHolder(View itemView) {
            super(itemView);
        }
    }

    class OtherHolder extends RecyclerView.ViewHolder {
        TextView itemTv;

        public OtherHolder(View itemView) {
            super(itemView);
            itemTv = itemView.findViewById(R.id.item_tv);
        }
    }
}
