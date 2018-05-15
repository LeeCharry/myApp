package com.example.jack.myapp.wanandroid.activity;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.EncodeUtils;
import com.example.jack.myapp.R;
import com.example.jack.myapp.wanandroid.BaseActivity;

import org.w3c.dom.Text;

/**
 * Created by lcy on 2018/5/14.
 */

public class AboutUsActivity extends BaseActivity {
    private TextView tvWebsiteContent;
    private Toolbar toolbar;
    private TextView tvAppversion;
    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void initView() {
        tvWebsiteContent = findViewById(R.id.tv_website_content);
        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        tvAppversion = findViewById(R.id.tv_appversion);
        toolbar = findViewById(R.id.toolbar);
        tvAppversion.setText(AppUtils.getAppName()+"\n"+ AppUtils.getAppVersionName());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutUsActivity.this.finish();
            }
        });

        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);

//        SpannableStringBuilder spannable = new SpannableStringBuilder();
//        spannable.append("\t网站内容\n\n\t\t本网站每天新增20~30篇优质文章，并加入到现有\n\t分类中，力求整理出一份优质而又详尽的知识体\n\t系，闲暇时间不妨上来学习下知识；除此之外，并未\n\t大家提供平时开发过程中常用的工具以及常用的网址\n\t导航。\n\n\t\t当然这只是我们目前的的功能，未来我们将提供更多\n\t\t更多便捷的功能...\n\n\t\t如果您有任何好的建议：\n\n\t\t\t关于网站排版  关于新增常用网址以及工具 未来\n\t\t你希望增加的功能等\n\n\t\t\t可以在");
//        spannable.append("项目中以issue的形式提出，我将及时跟进。\n\n\t\t\t如果您希望长期关注本站，可以加入我们\n\t\t的QQ群：591683946");
//        tvWebsiteContent.setText(spannable.toString());

        tvWebsiteContent.setText(Html.fromHtml(getString(R.string.about_content)).toString());
        tvWebsiteContent.setMovementMethod(LinkMovementMethod.getInstance());
//        collapsingToolbar.setContentScrimColor(Color.WHITE);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_about_us;
    }
}
