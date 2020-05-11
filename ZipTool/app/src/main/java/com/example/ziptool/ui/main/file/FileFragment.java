package com.example.ziptool.ui.main.file;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ziptool.R;
import com.example.ziptool.adapter.TabViewpagerAdapter;
import com.example.ziptool.databinding.FileFragmentBinding;
import com.example.ziptool.ui.main.file.twofile.unzip.UnZipFragment;
import com.example.ziptool.ui.main.file.twofile.zip.ZipFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FileFragment extends Fragment {

    private static final String TAG = "FileFragment";
    private FileViewModel mViewModel;
    private int[] TAB_TITLE = new int[]{R.string.file_zip,R.string.file_unZip};
    private FloatingActionButton fab;
    private static int LISTENER_FLAG = 0;
    private FileFragmentBinding binding;
    private View view;

    public static FileFragment newInstance() {
        return new FileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.file_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FileViewModel.class);
        view = binding.getRoot();
        initView();

    }

    private void initView() {
        List<Fragment> list = new ArrayList<>();
        list.add(new ZipFragment());
        list.add(new UnZipFragment());
        TabViewpagerAdapter adapter = new TabViewpagerAdapter(getContext(),getChildFragmentManager(),list,TAB_TITLE);
        binding.setAdapter(adapter);
        binding.setTab(binding.fileTabLayout);
        binding.setFragment(this);
        binding.fileTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: ");
                switch (tab.getPosition()){
                    case 0:
                        LISTENER_FLAG = 0;
                        break;
                    case 1:
                        LISTENER_FLAG = 1;
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void onFabClick(View v) {
        switch (LISTENER_FLAG){
            case 0:
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_fileFragment_to_fileFragment2);
                break;
            case 1:
                break;
            default:
                break;
        }
    }

    public void onTabSelected(TabLayout.Tab tab) {
        Log.d(TAG, "onTabSelected: ");
        switch (tab.getPosition()){
            case 0:
                LISTENER_FLAG = 0;
                Log.d(TAG, "onTabSelected: "+LISTENER_FLAG);
                break;
            case 1:
                LISTENER_FLAG = 1;
                Log.d(TAG, "onTabSelected: "+LISTENER_FLAG);
                break;
        }
    }

}
