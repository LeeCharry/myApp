package com.example.jack.myapp.demo.customize;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jack.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcy on 2018/8/6.
 */

public class PasswordBoxView extends LinearLayout {
    private  Animator downToUpAnimator;
    private  Animator upToDownAnimator;
    private ImageView ivBack;
    private TextView tv0, tv1, tv2, tv3, tv4, tv5;
    private GridView gvKeybord;
    private List<TextView> tvList = new ArrayList<>();
    private List<String> keybordDataList = new ArrayList<>();
    private List<String>  inputKeyList = new ArrayList<>();

    private PasswordBoxView passwordBoxView;

    public PasswordBoxView(Context context) {
        this(context, null);
    }

    public PasswordBoxView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordBoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.passwordBoxView = this;
        View contentview = LayoutInflater.from(context).inflate(R.layout.layout_password_box, null);
        initContentView(contentview);
        passwordBoxView.addView(contentview);
        initGvData();
        initListener();

        //设置背景透明和高度
        setAlpha(0.5f);
        int heightParam = context.getResources().getDisplayMetrics().heightPixels * 3/4;
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, heightParam, heightParam);
        setLayoutParams(lp);


//        LayoutTransition layoutTransition = new LayoutTransition();
//         downToUpAnimator = ObjectAnimator.ofFloat(this,"translationY",)
//        layoutTransition.setAnimator(LayoutTransition.APPEARING,downToUpAnimator);
//        setLayoutTransition();
    }


    private void initListener() {
        tv5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null != onInputCompleteListener) {
                    if (inputKeyList.indexOf("") == 6) {
                        //密码输入完成监听
                        onInputCompleteListener.inputComplete(list2Str(inputKeyList));
                    }

                }
            }
        });
    }

    private String list2Str(List<String> inputKeyList) {
        StringBuilder passStr = new StringBuilder();
        for (int i = 0; i < inputKeyList.size() ; i++) {
            passStr.append(inputKeyList.get(i));
        }
        return passStr.toString();
    }

    private void initGvData() {
        for (int i = 0; i < 9; i++) {
            keybordDataList.add(String.valueOf(i + 1));
        }
        keybordDataList.add("");
        keybordDataList.add("0");
        keybordDataList.add("<< -");
        KeyBordAdapter keyBordAdapter = new KeyBordAdapter();
        gvKeybord.setAdapter(keyBordAdapter);

        clearInput(inputKeyList);
        //键盘的点击事件处理
        gvKeybord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 11:
                        //删除
                        if (inputKeyList.indexOf("") > 0) {

                            setPassTv(inputKeyList.indexOf("")-1,false,"");
                        }
                        break;
                    case 9:
                        //空白
                        break;
                        default:
                            if (inputKeyList.indexOf("") == 6) {
                                //限制输入只能六位
                                return;
                            }
                            setPassTv(inputKeyList.indexOf(""),true,keybordDataList.get(position));
                       break;
                }
            }
        });
    }

    /**
     *  @param postion 操作的索引
     * @param isAdd  添加/删除
     */
    private void setPassTv(int postion, boolean isAdd, String input) {
        if (!isAdd){
            inputKeyList.set(postion,input);
        }else{
            inputKeyList.set(postion,input);
        }
        tvList.get(postion).setText(inputKeyList.get(postion));
    }


    /**
     * 清空输入的密码
     * @param inputKeyList
     */
    private void clearInput(List<String> inputKeyList) {
        inputKeyList.clear();
        for (int i = 0; i < 7 ; i++) {
            inputKeyList.add("");
        }
    }

    private void initContentView(View contentview) {
        ivBack = contentview.findViewById(R.id.iv_back);
        tv0 = contentview.findViewById(R.id.tv0);
        tv1 = contentview.findViewById(R.id.tv1);
        tv2 = contentview.findViewById(R.id.tv2);
        tv3 = contentview.findViewById(R.id.tv3);
        tv4 = contentview.findViewById(R.id.tv4);
        tv5 = contentview.findViewById(R.id.tv5);
        gvKeybord = contentview.findViewById(R.id.gv_keybord);

        tvList.add(tv0);
        tvList.add(tv1);
        tvList.add(tv2);
        tvList.add(tv3);
        tvList.add(tv4);
        tvList.add(tv5);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private class KeyBordAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return keybordDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return keybordDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keybord,null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv.setText(keybordDataList.get(position));
            if (position == 9 || position == 11) {
                viewHolder.tv.setBackgroundColor(getResources().getColor(R.color.bar_grey_90));
            }
            return convertView;
        }

        class ViewHolder {
            TextView tv;

            public ViewHolder(View convertView) {
                tv = convertView.findViewById(R.id.tv_keybord);
            }
        }
    }

    public void setOnInputCompleteListener(OnInputCompleteListener onInputCompleteListener) {
        this.onInputCompleteListener = onInputCompleteListener;
    }

    private OnInputCompleteListener onInputCompleteListener;
    interface OnInputCompleteListener{
        void inputComplete(String input);
    }
}
