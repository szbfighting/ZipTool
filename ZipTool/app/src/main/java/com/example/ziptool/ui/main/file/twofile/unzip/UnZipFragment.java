package com.example.ziptool.ui.main.file.twofile.unzip;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ziptool.R;

import java.io.File;

public class UnZipFragment extends Fragment {

    private UnZipViewModel mViewModel;

    public static UnZipFragment newInstance() {
        return new UnZipFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.un_zip_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UnZipViewModel.class);
        String path = Environment.getExternalStorageDirectory().toString();
        File file = new File(path+File.separator+"ZipTool"+File.separator+"UnZip");
        if (!file.exists()){
            if (file.mkdirs()){
                Log.d("FileCreate", "onActivityCreated: ");
            }
        }
    }

}
