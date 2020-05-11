package com.example.ziptool.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    public static void fileToZip(String filePath,String zipPath){
        try {
            File file = new File(filePath);
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath));
            doZip(zipOutputStream,file,"");
            zipOutputStream.finish();
            zipOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doZip(ZipOutputStream zipout, File file,String addPath){

        if (file.isDirectory()){
            addPath+=file.getName()+File.separator;
            File[] files = file.listFiles();
            for (File file1 : files) {
                doZip(zipout,file1,addPath);
            }
        }else{
            addPath+=file.getName();
            InputStream inputStream;
            BufferedInputStream buff;

            try {
                zipout.putNextEntry(new ZipEntry(addPath));
                inputStream = new FileInputStream(file);
                buff = new BufferedInputStream(inputStream);
                byte[] b = new byte[1024];
                int a =0;
                while((a=buff.read(b))!=-1){
                    zipout.write(b,0,a);
                }
                buff.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void zipToFile(String zipPath,String filePath) throws IOException {
        File file = new File(zipPath);
        if (!file.exists()){
            System.out.println("压缩文件不存在!");
            return;
        }
        ZipFile zipFile = new ZipFile(file);
        unZip(zipFile,filePath,filePath);
    }


    public static void unZip(ZipFile zipFile,String filePath,String destPath){
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while(entries.hasMoreElements()){
            ZipEntry entry = new ZipEntry(entries.nextElement());
            if(entry.isDirectory()){
                String path = destPath+File.separator+entry.getName();
                System.out.println(path);
                File dir = new File(path);
                dir.mkdirs();
            }else{
                String path = destPath+File.separator+entry.getName();
                System.out.println(path);
                File file = new File(path);
                if (!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                try {
                    file.createNewFile();
                    InputStream io = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(file);
                    int len;
                    byte[] size = new byte[1024];
                    while((len = io.read(size))!=-1){
                        fos.write(size,0,len);
                    }
                    io.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
