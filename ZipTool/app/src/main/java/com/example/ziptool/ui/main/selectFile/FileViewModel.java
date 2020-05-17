package com.example.ziptool.ui.main.selectFile;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ziptool.bean.FileInfo;
import com.example.ziptool.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class FileViewModel extends ViewModel {


    public static final int DOC_FILE = 0;
    public static final int ZIP_FILE = 1;
    public static final int PPT_FILE = 2;
    public static final int PDF_FILE = 3;
    public static final int TXT_FILE = 4;
    public static final int XLS_FILE = 5;
    private MutableLiveData<List<FileInfo>> allFile;
    private MutableLiveData<List<FileInfo>> docFile;
    private MutableLiveData<List<FileInfo>> pdfFile;
    private MutableLiveData<List<FileInfo>> pptFile;
    private MutableLiveData<List<FileInfo>> txtFile;
    private MutableLiveData<List<FileInfo>> xlsFile;
    private MutableLiveData<List<FileInfo>> fileToZip;
    private MutableLiveData<List<FileInfo>> zipFile;

    public FileViewModel() {
       init();
        Log.d("ViewModelConstructor", "FileViewModel: ");
    }

    public void init(){
        allFile = new MutableLiveData<>(new ArrayList<>());
        docFile = new MutableLiveData<>(new ArrayList<>());
        zipFile = new MutableLiveData<>(new ArrayList<>());
        pptFile = new MutableLiveData<>(new ArrayList<>());
        pdfFile = new MutableLiveData<>(new ArrayList<>());
        txtFile = new MutableLiveData<>(new ArrayList<>());
        xlsFile = new MutableLiveData<>(new ArrayList<>());
        new QueryTask().execute(DOC_FILE);
        new QueryTask().execute(ZIP_FILE);
        new QueryTask().execute(PPT_FILE);
        new QueryTask().execute(PDF_FILE);
        new QueryTask().execute(TXT_FILE);
        new QueryTask().execute(XLS_FILE);
    }


    class QueryTask extends AsyncTask<Integer,Void,List<FileInfo>>{

        List<FileInfo> list = new ArrayList<>();
        int index;
        @Override
        protected List<FileInfo> doInBackground(Integer... index) {
            switch (index[0]){
                case DOC_FILE:
                    this.index = DOC_FILE;
                    list = FileUtil.getDocFile();
                    break;
                case ZIP_FILE:
                    this.index = ZIP_FILE;
                    list = FileUtil.getZipFile();
                    break;
                case PPT_FILE:
                    this.index = PPT_FILE;
                    list = FileUtil.getPptFile();
                    break;
                case PDF_FILE:
                    this.index = PDF_FILE;
                    list = FileUtil.getPdfFile();
                    break;
                case TXT_FILE:
                    this.index = TXT_FILE;
                    list = FileUtil.getTxtFile();
                    break;
                case XLS_FILE:
                    this.index = XLS_FILE;
                    list = FileUtil.getXlsFile();
                    break;
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<FileInfo> fileInfos) {
            super.onPostExecute(fileInfos);
            switch (index){
                case DOC_FILE:
                   docFile.setValue(list);
                    break;
                case ZIP_FILE:
                   zipFile.setValue(list);
                    break;
                case PPT_FILE:
                    pptFile.setValue(list);
                    break;
                case PDF_FILE:
                    pdfFile.setValue(list);
                    break;
                case TXT_FILE:
                    txtFile.setValue(list);
                    break;
                case XLS_FILE:
                    xlsFile.setValue(list);
                    break;
            }
        }
    }


    /**
     * 明天开线程，耗时操作
     */



    public MutableLiveData<List<FileInfo>> getAllFile() {
        List<FileInfo> list = new ArrayList<>();
        list.addAll(getDocFile().getValue());
        list.addAll(getPdfFile().getValue());
        list.addAll(getPptFile().getValue());
        list.addAll(getTxtFile().getValue());
        list.addAll(getXlsFile().getValue());
        allFile.setValue(list);
        return allFile;
    }


    public MutableLiveData<List<FileInfo>> getDocFile() {
        return docFile;
    }


    public MutableLiveData<List<FileInfo>> getZipFile() {
        return zipFile;
    }


    public MutableLiveData<List<FileInfo>> getPptFile() {
        return pptFile;
    }

    public MutableLiveData<List<FileInfo>> getPdfFile() {
        return pdfFile;
    }

    public MutableLiveData<List<FileInfo>> getTxtFile() {
        return txtFile;
    }

    public MutableLiveData<List<FileInfo>> getXlsFile() {
        return xlsFile;
    }


    public void addFileToZip(FileInfo fileInfo) {
        if (fileToZip == null) {
            fileToZip = new MutableLiveData<>();
            fileToZip.setValue(new ArrayList<>());
        }
        List<FileInfo> list = fileToZip.getValue();
        if (!list.contains(fileInfo)) {
            list.add(fileInfo);
            fileToZip.setValue(list);
        }
    }


    public MutableLiveData<List<FileInfo>> getFileToZip() {
        if (fileToZip == null) {
            fileToZip = new MutableLiveData<>();
            fileToZip.setValue(new ArrayList<>());
        }
        return fileToZip;
    }

    public void removeFileToZip(FileInfo fileInfo) {
        List<FileInfo> list = fileToZip.getValue();
        list.remove(fileInfo);
        fileToZip.setValue(list);
    }

}
