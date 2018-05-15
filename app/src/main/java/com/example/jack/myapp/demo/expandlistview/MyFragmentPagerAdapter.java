package com.example.jack.myapp.demo.expandlistview;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lcy on 2018/5/10.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragmentList;
    private  String[]  titles;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles ) {
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
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
