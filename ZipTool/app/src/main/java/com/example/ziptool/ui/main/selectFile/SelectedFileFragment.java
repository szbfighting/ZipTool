package com.example.ziptool.ui.main.selectFile;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ziptool.R;
import com.example.ziptool.adapter.FileItemAdapter;
import com.example.ziptool.bean.FileInfo;
import com.example.ziptool.databinding.FragmentSelectdeFileBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectedFileFragment extends Fragment {

    private FileViewModel fileViewModel;
    private FragmentSelectdeFileBinding binding;
    private FileItemAdapter adapter;


    public static SelectedFileFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("TITLE", title);
        SelectedFileFragment fragment = new SelectedFileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SelectedFileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_selectde_file, container, false);

        return binding.getRoot();
    }

    /**
     * uri = content://media/external/file,selection=(_data LIKE '%.doc'),sortOrder=_size asc
     */


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fileViewModel = ViewModelProviders.of(getParentFragment()).get(FileViewModel.class);
        Bundle bundle = getArguments();
        String title = bundle.getString("TITLE");
        TextView textView = getView().findViewById(R.id.title_tv);
        textView.setText(title);
        checkFragment(title);
        //adapter.setListZip(fileViewModel.getFileToZip().getValue());
        fileViewModel.getFileToZip().observe(getParentFragment().getViewLifecycleOwner(), new Observer<List<FileInfo>>() {
            @Override
            public void onChanged(List<FileInfo> list) {
                adapter.setListZip(list);
                if (binding.fileRv.isComputingLayout()){
                    binding.fileRv.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }else{
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void checkFragment(String title) {
        adapter = new FileItemAdapter(getContext(),title);
        switch (title) {
            case "ALL":{
                adapter.addAllFiles(fileViewModel.getAllFile().getValue());
                binding.setAdapter(adapter);

            }
            break;
            case "DOC": {
                adapter.addAllFiles(fileViewModel.getDocFile().getValue());
                binding.setAdapter(adapter);

            }
            break;
            case "PPT": {
                adapter.addAllFiles(fileViewModel.getPptFile().getValue());
                binding.setAdapter(adapter);
            }
            break;
            case "PDF": {
                adapter.addAllFiles(fileViewModel.getPdfFile().getValue());
                binding.setAdapter(adapter);
            }
            break;
            case "TXT": {
                adapter.addAllFiles(fileViewModel.getTxtFile().getValue());
                binding.setAdapter(adapter);
            }
            break;
            case "XLS": {
                adapter.addAllFiles(fileViewModel.getXlsFile().getValue());
                binding.setAdapter(adapter);
            }
            break;
        }
        adapter.setZipListener((fileInfo, isChecked) -> {
            if (isChecked) {
                fileViewModel.addFileToZip(fileInfo);
            } else {
                fileViewModel.removeFileToZip(fileInfo);
            }
        });
        //adapter.setListener(fileInfo -> Toast.makeText(getContext(), "Click " + title, Toast.LENGTH_SHORT).show());
    }
}
