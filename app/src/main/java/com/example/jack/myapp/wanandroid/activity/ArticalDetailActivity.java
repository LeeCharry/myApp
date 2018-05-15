package com.example.jack.myapp.wanandroid.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.AppConstant;
import com.example.jack.myapp.R;
import com.example.jack.myapp.bean.Artical;
import com.example.jack.myapp.mvp.contract.MarkedContract;
import com.example.jack.myapp.mvp.presenter.MarkedPresenter;
import com.example.jack.myapp.wanandroid.BaseActivity;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;

/**
 * Created by lcy on 2018/5/8.
 * 文章详情页
 */

public class ArticalDetailActivity extends BaseActivity implements MarkedContract.View {
    private Toolbar toolbar;
    //    private WebView webview;
    private MarkedPresenter mPresenter;
    private String link;
    private long articalId;
    private FrameLayout framWeb;


    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
//        webview = findViewById(R.id.webview);
        framWeb = findViewById(R.id.fram_web);

        mPresenter = new MarkedPresenter(this, this);
        setRightMenu();
        showData();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticalDetailActivity.this.finish();
            }
        });

        setWebAgent();
    }

    private void setWebAgent() {
        AgentWeb.with(this)//传入Activity or Fragment
                .setAgentWebParent(framWeb, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
               .setReceivedTitleCallback(mReceivedTitleCallback) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go(link);
    }


    /**
     * toolbar设置右侧菜单项
     */
    private void setRightMenu() {
        toolbar.inflateMenu(R.menu.menu_artical_detail);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_share:
                        //分享
                        Intent textIntent = new Intent(Intent.ACTION_SEND);
                        textIntent.setType("text/plain");
                        textIntent.putExtra(Intent.EXTRA_TEXT, link);
                        startActivity(Intent.createChooser(textIntent, "分享"));
                        break;
                    case R.id.item_marke:
                        //收藏
                        mPresenter.collectArtical(articalId);
                        break;
                    case R.id.item_system_browser:
                        //用系统浏览器打开
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(link);
                        intent.setData(content_url);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    private void showData() {
        if (null != getIntent().getStringExtra(AppConstant.TITLE)) {
            String title = getIntent().getStringExtra(AppConstant.TITLE);
            toolbar.setTitle(R.string.loading_now);
        }
        articalId = getIntent().getLongExtra(AppConstant.ID, 0);
        if (null != getIntent().getStringExtra(AppConstant.LINK)) {
            link = getIntent().getStringExtra(AppConstant.LINK);
//            webview.getSettings().setJavaScriptEnabled(true);
//            webview.loadUrl(link);
//            //解决webview加载url跳转到系统浏览器
//            webview.setWebViewClient(new WebViewClient(){
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    view.loadUrl(url);
//                    return super.shouldOverrideUrlLoading(view, url);
//                }
//            });
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_artical_detail;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.showShort(msg.toString());
    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMySelf() {

    }

    @Override
    public void getMarkedList(Artical artical) {

    }

    @Override
    public void unCollectArtical() {

    }

    @Override
    public void collectArtical() {
        ToastUtils.showShort("收藏成功");
    }

    private ChromeClientCallbackManager.ReceivedTitleCallback mReceivedTitleCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            toolbar.setTitle(title);
        }
    };
}
