package com.example.jack.myapp.demo.head_line;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by lcy on 2018/7/27.
 */

public class HeadlineAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;

    public HeadlineAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    public HeadlineAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        HeadlineFragment headlineFragment = (HeadlineFragment) fragmentList.get(position);
        String title = headlineFragment.getTitle().toString();
//        headlineFragment.setTitle(title);
        return title;
    }
}
