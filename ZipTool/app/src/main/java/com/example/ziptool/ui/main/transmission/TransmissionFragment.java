package com.example.ziptool.ui.main.transmission;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ziptool.R;
import com.example.ziptool.adapter.TabViewpagerAdapter;
import com.example.ziptool.ui.main.transmission.twotransmission.bluetooth.BlueToothFragment;
import com.example.ziptool.ui.transmission.wifi.WifiFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransmissionFragment extends Fragment {

    private static final int[] TAB_TITLE = new int[]{R.string.trans_blue_tooth,R.string.trans_wifi};

    private static final String TAG = "TransmissionFragment";
    private ViewPager viewPager;
    private TabLayout tab;

    public TransmissionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.transmission_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Fragment> list = new ArrayList<>();
        list.add(new BlueToothFragment());
        list.add(new WifiFragment());
        View view = getView();
        viewPager = view.findViewById(R.id.trans_viewpager);
        TabViewpagerAdapter adapter = new TabViewpagerAdapter(getContext(),getChildFragmentManager(),list,TAB_TITLE);
        viewPager.setAdapter(adapter);
        tab = view.findViewById(R.id.trans_tab);
        tab.setupWithViewPager(viewPager);
        Log.d(TAG, "onActivityCreated: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");

    }
}
