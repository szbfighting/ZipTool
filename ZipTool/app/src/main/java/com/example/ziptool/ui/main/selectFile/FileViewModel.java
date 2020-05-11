package com.example.ziptool.ui.main.selectFile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ziptool.bean.FileInfo;
import com.example.ziptool.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class FileViewModel extends ViewModel {

    private MutableLiveData<List<FileInfo>> allFile;
    private MutableLiveData<List<FileInfo>> docFile;
    private MutableLiveData<List<FileInfo>> pdfFile;
    private MutableLiveData<List<FileInfo>> pptFile;
    private MutableLiveData<List<FileInfo>> txtFile;
    private MutableLiveData<List<FileInfo>> xlsFile;
    private MutableLiveData<List<FileInfo>> fileToZip;
    private MutableLiveData<List<FileInfo>> zipFile;

    /**
     * 明天开线程，耗时操作
     */

    public MutableLiveData<List<FileInfo>> getAllFile() {
        if (allFile == null) {
            allFile = new MutableLiveData<>();
            allFile.setValue(new ArrayList<FileInfo>());
            List<FileInfo> list = new ArrayList<>();
            list.addAll(getDocFile().getValue());
            list.addAll(getPdfFile().getValue());
            list.addAll(getPptFile().getValue());
            list.addAll(getTxtFile().getValue());
            list.addAll(getXlsFile().getValue());
            allFile.setValue(list);
        }
        return allFile;
    }


    public MutableLiveData<List<FileInfo>> getDocFile() {
        if (docFile == null) {
            docFile = new MutableLiveData<>();
            docFile.setValue(new ArrayList<FileInfo>());
        }
        docFile.setValue(FileUtil.getDocFile());
        return docFile;
    }


    public MutableLiveData<List<FileInfo>> getZipFile() {
        if (zipFile == null) {
            zipFile = new MutableLiveData<>();
            zipFile.setValue(new ArrayList<FileInfo>());
        }
        zipFile.setValue(FileUtil.getZipFile());
        return zipFile;
    }


    public MutableLiveData<List<FileInfo>> getPptFile() {
        if (pptFile == null) {
            pptFile = new MutableLiveData<>();
        }
        pptFile.setValue(FileUtil.getPptFile());
        return pptFile;
    }

    public MutableLiveData<List<FileInfo>> getPdfFile() {
        if (pdfFile == null) {
            pdfFile = new MutableLiveData<>();
        }
        pdfFile.setValue(FileUtil.getPdfFile());
        return pdfFile;
    }

    public MutableLiveData<List<FileInfo>> getTxtFile() {
        if (txtFile == null) {
            txtFile = new MutableLiveData<>();
        }
        txtFile.setValue(FileUtil.getTxtFile());
        return txtFile;
    }

    public MutableLiveData<List<FileInfo>> getXlsFile() {
        if (xlsFile == null) {
            xlsFile = new MutableLiveData<>();
        }
        xlsFile.setValue(FileUtil.getXlsFile());
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
