package com.example.ziptool.ui.main;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.ziptool.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZipPathFragment extends Fragment {


    public static final String PATH_KEY = "zip_path";


    public ZipPathFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zip_path, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        EditText path_et = view.findViewById(R.id.path_et);
        Bundle bundle = getArguments();
        String defaultPath = bundle.getString(PATH_KEY);
        path_et.setText(defaultPath);
    }
}
