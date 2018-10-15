package com.example.jack.myapp.demo.video_play;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.dingmouren.layoutmanagergroup.viewpager.OnViewPagerListener;
import com.dingmouren.layoutmanagergroup.viewpager.ViewPagerLayoutManager;
import com.example.jack.myapp.R;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoPlayListActivity extends AppCompatActivity {

    private static final String TAG = "ViewPagerActivity";
    private RecyclerView mRecyclerView;
    private MyAdapter2 mAdapter;
    private ViewPagerLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play_list);
        initView();
        initListener();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler);

        mLayoutManager = new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);
        mAdapter = new MyAdapter2();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {

            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                Log.e(TAG, "释放位置:" + position + " 下一页:" + isNext);
                int index = 0;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
//                releaseVideo(index);
                releaseVideo2(index);
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                Log.e(TAG, "选中位置:" + position + "  是否是滑动到底部:" + isBottom);
//                playVideo(0);
                playVideo2(position);
            }

            public void onLayoutComplete() {
//                playVideo(0);
                playVideo2(0);
            }
        });
    }

    private void playVideo(int position) {
        View itemView = mRecyclerView.getChildAt(0);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        videoView.start();
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                Log.e(TAG, "onInfo");
                mp.setLooping(true);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.e(TAG, "onPrepared");

            }
        });


        imgPlay.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;

            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    Log.e(TAG, "isPlaying:" + videoView.isPlaying());
                    imgPlay.animate().alpha(1f).start();
                    videoView.pause();
                    isPlaying = false;
                } else {
                    Log.e(TAG, "isPlaying:" + videoView.isPlaying());
                    imgPlay.animate().alpha(0f).start();
                    videoView.start();
                    isPlaying = true;
                }
            }
        });
    }

    private void playVideo2(int position) {
        View itemView = mRecyclerView.getChildAt(0);
        JzvdStd jzvdStd = itemView.findViewById(R.id.jzvdStd);
        jzvdStd.setUp(videos[position % videos.length], "", Jzvd.SCREEN_WINDOW_NORMAL);
        Glide.with(VideoPlayListActivity.this)
                .load(imgs[position % imgs.length])
                .into(jzvdStd.thumbImageView);
        jzvdStd.startVideo();
    }

    private void releaseVideo(int index) {
        View itemView = mRecyclerView.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        videoView.stopPlayback();
        imgThumb.animate().alpha(1).start();
        imgPlay.animate().alpha(0f).start();
    }

    private void releaseVideo2(int index) {
        View itemView = mRecyclerView.getChildAt(index);
        final JzvdStd jzvdStd = itemView.findViewById(R.id.jzvdStd);
        jzvdStd.release();

    }

    private String[] imgs = {"http://img5.mtime.cn/mg/2018/07/18/232304.69991312_120X90X4.jpg", "http://img5.mtime.cn/mg/2018/07/19/084445.53397431_120X90X4.jpg", "http://img5.mtime.cn/mg/2018/07/18/093816.32257167_120X90X4.jpg", "http://img5.mtime.cn/mg/2018/07/17/212640.47261269_120X90X4.jpg", "http://img5.mtime.cn/mg/2018/07/17/201020.62975373_120X90X4.jpg", "http://img5.mtime.cn/mg/2018/07/18/082059.21524989_120X90X4.jpg"};
    private String[] videos = {"http://vfx.mtime.cn/Video/2018/07/18/mp4/180718232313635659.mp4", "http://vfx.mtime.cn/Video/2018/07/19/mp4/180719084511280696.mp4", "http://vfx.mtime.cn/Video/2018/07/18/mp4/180718093832287666.mp4", "http://vfx.mtime.cn/Video/2018/07/17/mp4/180717212809007443.mp4", "http://vfx.mtime.cn/Video/2018/07/17/mp4/180717201026773010.mp4", "http://vfx.mtime.cn/Video/2018/08/23/mp4/180823222144907956.mp4"};

    class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager2, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.jzvdStd.setUp(videos[position % videos.length], "", Jzvd.SCREEN_WINDOW_NORMAL);
            Glide.with(VideoPlayListActivity.this)
                    .load(imgs[position % imgs.length])
                    .into(holder.jzvdStd.thumbImageView);
            if (position == 0) {
                holder.jzvdStd.startVideo();
            }
        }

        @Override
        public int getItemCount() {
            return 30;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            JzvdStd jzvdStd;

            public ViewHolder(View itemView) {
                super(itemView);
                jzvdStd = itemView.findViewById(R.id.jzvdStd);
            }
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private int[] imgs = {R.mipmap.video_img01, R.mipmap.video_img01, R.mipmap.video_img01, R.mipmap.video_img01, R.mipmap.video_img01, R.mipmap.video_img01, R.mipmap.video_img01, R.mipmap.video_img01, R.mipmap.video_img01};
        //        private String[] videos = {R.raw.video11,R.raw.video12,R.raw.video13,R.raw.video14,R.raw.video_2};
//        private String[] videos = {"http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","http://v.ysbang.cn//data/video/2015/rkb/2015rkb01.mp4","http://vfx.mtime.cn/Video/2018/09/07/mp4/180907135906609714.mp4"};
        private String[] videos = {"http://vfx.mtime.cn/Video/2018/09/03/mp4/180903111516292241.mp4", "http://vfx.mtime.cn/Video/2018/08/30/mp4/180830230725882639.mp4", "http://vfx.mtime.cn/Video/2018/09/06/mp4/180906083142712290.mp4", "http://vfx.mtime.cn/Video/2018/09/03/mp4/180903111516292241.mp4", "http://vfx.mtime.cn/Video/2018/08/29/mp4/180829082744092695.mp4", "http://vfx.mtime.cn/Video/2018/08/28/mp4/180828152314850264.mp4", "http://vfx.mtime.cn/Video/2018/08/23/mp4/180823222144907956.mp4", "http://vfx.mtime.cn/Video/2018/08/24/mp4/180824104230502641.mp4", "http://vfx.mtime.cn/Video/2018/08/24/mp4/180824104230502641.mp4"};

        public MyAdapter() {
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
//            holder.img_thumb.setImageResource(imgs[position%4]);
            Glide.with(VideoPlayListActivity.this).load("http://img5.mtime.cn/mg/2018/08/30/230722.32223160_120X90X4.jpg").into(holder.img_thumb);
//            holder.img_thumb.setImageURI(Uri.parse("http://img5.mtime.cn/mg/2018/08/30/230722.32223160_120X90X4.jpg"));
//          holder.videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+ videos[position%5]));
            holder.videoView.setVideoURI(Uri.parse(videos[position % videos.length]));
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img_thumb;
            VideoView videoView;
            ImageView img_play;
            RelativeLayout rootView;

            public ViewHolder(View itemView) {
                super(itemView);
                img_thumb = itemView.findViewById(R.id.img_thumb);
                videoView = itemView.findViewById(R.id.video_view);
                img_play = itemView.findViewById(R.id.img_play);
                rootView = itemView.findViewById(R.id.root_view);
            }
        }
    }
}
