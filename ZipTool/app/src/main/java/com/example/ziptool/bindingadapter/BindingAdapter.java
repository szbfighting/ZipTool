package com.example.ziptool.bindingadapter;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.ziptool.adapter.FileItemAdapter;
import com.example.ziptool.adapter.TabViewpagerAdapter;
import com.example.ziptool.application.MyApplication;
import com.example.ziptool.bean.FileInfo;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class BindingAdapter {

    @androidx.databinding.BindingAdapter(value = {"adapter","tab"})
    public static void VpTabAdapter(ViewPager viewPager, TabViewpagerAdapter adapter, TabLayout tabLayout){
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @androidx.databinding.BindingAdapter(value = {"rvadapter"})
    public static void RvAdapter(RecyclerView recyclerView, FileItemAdapter adapter){
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public static void LvAdapter(ListView listView, ArrayAdapter arrayAdapter){
        listView.setAdapter(arrayAdapter);
    }
}
