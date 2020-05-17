package com.example.ziptool.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ziptool.R;
import com.example.ziptool.bean.FileInfo;
import com.example.ziptool.databinding.RvFileItemBinding;

import java.util.ArrayList;
import java.util.List;


public class FileItemAdapter extends RecyclerView.Adapter<FileItemAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private List<FileInfo> list;
    private FileCheckListener listener;
    private FileToZipListener zipListener;
    private List<FileInfo> listZip;
    private String title;
    private RvFileItemBinding binding;

    public FileItemAdapter(Context context,String title) {
        inflater = LayoutInflater.from(context);
        list = new ArrayList<>();
        this.title = title;
    }

    public void addAllFiles(List<FileInfo> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_file_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FileInfo fileInfo = list.get(position);
        binding = (RvFileItemBinding) holder.getBinding();
        binding.setVariable(com.example.ziptool.BR.fileInfo, fileInfo);
        binding.executePendingBindings();
        //holder.itemView.setOnClickListener(v -> listener.onCheck(fileInfo));

        binding.isZipSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> zipListener.onZip(fileInfo, isChecked));
        setFileImage(fileInfo);
        if (listZip.contains(fileInfo)) {
            binding.isZipSwitch.setChecked(true);
        } else {
            binding.isZipSwitch.setChecked(false);
        }
    }

    private void setFileImage(FileInfo fileInfo) {
        switch (fileInfo.getFileType()) {
            case "DOC": {
                binding.imageView.setImageResource(R.drawable.ic_insert_drive_file_blue_24dp);
            }
            break;
            case "PPT": {
                binding.imageView.setImageResource(R.drawable.ic_insert_drive_file_yellow_24dp);
            }
            break;
            case "PDF": {
                binding.imageView.setImageResource(R.drawable.ic_insert_drive_file_red_24dp);
            }
            break;
            case "TXT": {
                binding.imageView.setImageResource(R.drawable.ic_insert_drive_file_grey_24dp);
            }
            break;
            case "XLS": {
                binding.imageView.setImageResource(R.drawable.ic_insert_drive_file_perple_24dp);
            }
            break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

        private T binding;

        public ViewHolder(T binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public T getBinding() {
            return binding;
        }
    }

    public void setListener(FileCheckListener listener) {
        this.listener = listener;
    }

    public void setZipListener(FileToZipListener listener) {
        zipListener = listener;
    }

    public interface FileCheckListener {
        void onCheck(FileInfo fileInfo);
    }

    public interface FileToZipListener {
        void onZip(FileInfo fileInfo, boolean isChecked);
    }

    public void setListZip(List<FileInfo> listZip) {
        this.listZip = listZip;

        /*new Handler().post(new Runnable() {
            @Override
            public void run() {
                Log.d("NOTIFY THREAD", "run: "+Thread.currentThread().getName());
                notifyDataSetChanged();
            }
        });*/
    }
}
