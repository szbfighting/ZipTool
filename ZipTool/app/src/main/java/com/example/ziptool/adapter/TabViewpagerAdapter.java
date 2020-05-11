package com.example.ziptool.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class TabViewpagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> fragments;
    private int[] title;
    public TabViewpagerAdapter(Context context, @NonNull FragmentManager fm, List<Fragment> fragments, int[] title) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.title = title;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(title[position]);
    }
}
