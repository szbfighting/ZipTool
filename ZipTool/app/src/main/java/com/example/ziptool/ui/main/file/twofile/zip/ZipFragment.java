package com.example.ziptool.ui.main.file.twofile.zip;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ziptool.R;

import java.io.File;

public class ZipFragment extends Fragment {

    private ZipViewModel mViewModel;

    public static ZipFragment newInstance() {
        return new ZipFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zip_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ZipViewModel.class);
        String path = Environment.getExternalStorageDirectory().toString();
        File file = new File(path+File.separator+"ZipTool"+File.separator+"Zip");
        if (!file.exists()){
            if (file.mkdirs()){
                Log.d("FileCreate", "onActivityCreated: ");
            }
        }
    }

}
