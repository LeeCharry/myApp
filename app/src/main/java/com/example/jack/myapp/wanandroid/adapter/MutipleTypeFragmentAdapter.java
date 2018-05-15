package com.example.jack.myapp.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jack.myapp.wanandroid.fragment.MutipleTypeFragment;

import java.util.List;

/**
 * Created by lcy on 2018/5/13.
 */

public class MutipleTypeFragmentAdapter extends FragmentPagerAdapter {
    private List<MutipleTypeFragment> fragmentList;
    private List<String> titles;

    public MutipleTypeFragmentAdapter(FragmentManager fm, List<MutipleTypeFragment> fragmentList, List<String> titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
