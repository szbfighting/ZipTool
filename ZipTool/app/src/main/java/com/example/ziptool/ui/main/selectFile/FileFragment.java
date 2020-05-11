package com.example.ziptool.ui.main.selectFile;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.example.ziptool.R;
import com.example.ziptool.adapter.TabViewpagerAdapter;
import com.example.ziptool.bean.FileInfo;
import com.example.ziptool.ui.main.ZipPathFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FileFragment extends Fragment {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

        }
    };

    private FileViewModel mViewModel;
    private ViewPager viewPager;
    private int[] TAB_TITLE = new int[]{
            R.string.selected_file_all,
            R.string.selected_file_pdf,
            R.string.selected_file_ppt,
            R.string.selected_file_doc,
            R.string.selected_file_txt,
            R.string.selected_file_xls
    };


    public static FileFragment newInstance() {
        return new FileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.file_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mViewModel = ViewModelProviders.of(this).get(FileViewModel.class);
        View view = getView();
        viewPager = view.findViewById(R.id.selected_file_vp);
        List<Fragment> list = new ArrayList<>();
        for (int i = 0; i < TAB_TITLE.length; i++) {
            SelectedFileFragment fileFragment = SelectedFileFragment.newInstance(getString(TAB_TITLE[i]));
            list.add(fileFragment);
        }
        TabViewpagerAdapter adapter = new TabViewpagerAdapter(getContext(), getChildFragmentManager(), list, TAB_TITLE);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = view.findViewById(R.id.selected_file_tab);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_file_select, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_file_zip:
                List<FileInfo> fileInfos = mViewModel.getFileToZip().getValue();
                for (FileInfo fileInfo : fileInfos) {
                    Log.d("startzip", "onOptionsItemSelected: " + fileInfo + "\n");
                }
                NavController controller = Navigation.findNavController(getView());
                Bundle bundle = new Bundle();
                String path = mViewModel.getFileToZip().getValue().get(0).getFileName();
                bundle.putString(ZipPathFragment.PATH_KEY,path.substring(0,path.indexOf('.')));
                controller.navigate(R.id.action_fileFragment2_to_zipPathFragment,bundle);
        }

        return true;
    }
}
