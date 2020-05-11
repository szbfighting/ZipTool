package com.example.ziptool.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.ziptool.application.MyApplication;
import com.example.ziptool.bean.FileCategory;
import com.example.ziptool.bean.FileInfo;
import com.example.ziptool.bean.SortMethod;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtil {

    private static final Context context = MyApplication.getContext();

    private static Uri getContentUriByCategory(FileCategory cat) {
        Uri uri;
        String volumeName = "external";
        switch (cat) {
            case Theme:
            case Doc:
            case Zip:
            case Apk:
            case Txt:
            case Pdf:
            case Ppt:
            case Xls:
            case Docx:
                uri = MediaStore.Files.getContentUri(volumeName);
                break;
            case Music:
                uri = MediaStore.Audio.Media.getContentUri(volumeName);
                break;
            case Video:
                uri = MediaStore.Video.Media.getContentUri(volumeName);
                break;
            case Picture:
                uri = MediaStore.Images.Media.getContentUri(volumeName);
                break;
            default:
                uri = null;

        }
        return uri;
    }

    private static String buildSelectionByCategory(FileCategory cat) {
        String selection = null;
        switch (cat) {
            case Theme:
                selection = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.mtz')";
                break;
            case Doc:
                selection = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.doc')";
                break;
            case Docx:
                selection = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.docx')";
                break;
            case Pdf:
                selection = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.pdf')";
                break;
            case Ppt:
                selection = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.ppt')";
                break;
            case Pptx:
                selection = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.pptx')";
                break;
            case Txt:
                selection = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.txt')";
                break;
            case Xls:
                selection = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.xls')";
                break;
            case Zip:
                selection = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.zip')";
                break;
            case Apk:
                selection = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.apk')";
                break;
            default:
                selection = null;
        }
        return selection;
    }


    private static String buildSortOrder(SortMethod sort) {
        String sortOrder = null;
        switch (sort) {
            case name:
                sortOrder = MediaStore.Files.FileColumns.TITLE + " asc";
                break;
            case size:
                sortOrder = MediaStore.Files.FileColumns.SIZE + " asc";
                break;
            case date:
                sortOrder = MediaStore.Files.FileColumns.DATE_MODIFIED + " desc";
                break;
            case type:
                sortOrder = MediaStore.Files.FileColumns.MIME_TYPE + " asc, " + MediaStore.Files.FileColumns.TITLE + " asc";
                break;

        }

        return sortOrder;
    }

    public static Cursor query(Context context, FileCategory fc, SortMethod sort) {
        Uri uri = getContentUriByCategory(fc);
        String selection = buildSelectionByCategory(fc);
        String sortOrder = buildSortOrder(sort);
        if (uri == null) {
            Log.d("FileUtil", "query: null" + fc.name());
            return null;
        }
        String[] columns = new String[]{
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.SIZE,
                MediaStore.Files.FileColumns.DATE_MODIFIED
        };

        return context.getContentResolver().query(uri, columns, selection, null, sortOrder);
    }



    public static List<FileInfo> getDocFile(){
        List<FileInfo> list  = new ArrayList<>();
        Cursor cursor = query(context,FileCategory.Doc,SortMethod.size);
        //Log.d("cursortime", "handleCursor: "+cursor.getCount());
        handleCursor(list,cursor,"DOC");
        cursor = query(context,FileCategory.Docx,SortMethod.size);
        handleCursor(list,cursor,"DOC");
        return list;
    }

    public static List<FileInfo> getZipFile(){
        List<FileInfo> list  = new ArrayList<>();
        Cursor cursor = query(context,FileCategory.Zip,SortMethod.size);
        //Log.d("cursortime", "handleCursor: "+cursor.getCount());
        handleCursor(list,cursor,"ZIP");
        return list;
    }

    public static List<FileInfo> getPdfFile(){
        List<FileInfo> list = new ArrayList<>();
        Cursor cursor = query(context,FileCategory.Pdf,SortMethod.size);
        handleCursor(list,cursor,"PDF");
        return list;
    }

    public static List<FileInfo> getPptFile(){
        List<FileInfo> list = new ArrayList<>();
        Cursor cursor = query(context,FileCategory.Ppt,SortMethod.size);
        handleCursor(list,cursor,"PPT");
        cursor = query(context,FileCategory.Pptx,SortMethod.size);
        handleCursor(list,cursor,"PPT");
        return list;
    }

    public static List<FileInfo> getXlsFile(){
        List<FileInfo> list = new ArrayList<>();
        Cursor cursor = query(context,FileCategory.Xls,SortMethod.size);
        handleCursor(list,cursor,"XLS");
        cursor = query(context,FileCategory.Xlsx,SortMethod.size);
        handleCursor(list,cursor,"XLS");
        return list;
    }

    public static List<FileInfo> getTxtFile(){
        List<FileInfo> list = new ArrayList<>();
        Cursor cursor = query(context,FileCategory.Txt,SortMethod.size);
        handleCursor(list,cursor,"TXT");
        return list;
    }


    public static List<FileInfo> handleCursor(List<FileInfo> list,Cursor cursor,String fileType){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date;
        FileInfo fileInfo;
        File file;
        //Log.d("cursor==null", "handleCursor: "+(cursor==null));

        if (cursor == null){
            return null;
        }
        while(cursor.moveToNext()){
            //Log.d("While", "handleCursor: ");
            int data = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
            int size = cursor.getColumnIndex(MediaStore.Files.FileColumns.SIZE);
            int modified = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATE_MODIFIED);
            String filePath = cursor.getString(data);
            String fileSize = cursor.getString(size);
            long fileModified = Long.valueOf(cursor.getString(modified))*1000;
            date = new Date(fileModified);
            String fileTime = dateFormat.format(date);
            file = new File(filePath);
            fileInfo = new FileInfo(file.getName(),fileTime,fileSize+"B",filePath);
            fileInfo.setFileType(fileType);
            //Log.d("handle", "handleCursor: "+fileInfo);
            list.add(fileInfo);
        }

        return list;
    }
}