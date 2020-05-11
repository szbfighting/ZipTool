package com.example.ziptool.ui.main.selectFile;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ziptool.R;
import com.example.ziptool.adapter.FileItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZipFileFragment extends Fragment {


    private RecyclerView recyclerView;
    //适配器得另外写，因为没有那个switch

    public ZipFileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zip_file, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        recyclerView = view.findViewById(R.id.zip_file_rv);
        
    }
}
