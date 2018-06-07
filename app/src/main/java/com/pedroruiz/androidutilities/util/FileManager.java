package com.pedroruiz.androidutilities.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileManager {
    public static byte[] file2ByteArray(File file){
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bytes;
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        return name.substring(name.lastIndexOf(".") + 1);
    }

    public static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf(".") + 1);
    }


    public static void openResource(Uri uri, Context context){
        ContentResolver cR = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        String type = mime.getExtensionFromMimeType(cR.getType(uri));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, type);
        context.startActivity(intent);
    }

    public static void openResource(File file, Context context){
        Uri uri = Uri.fromFile(file);
        ContentResolver cR = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        String type = mime.getExtensionFromMimeType(cR.getType(uri));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, type);
        context.startActivity(intent);
    }

    public static File getFileFromPath(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            return file;
        }
        return null;
    }

    public static File saveFileToFolder(String path, String fileName, byte[] data) throws IOException {

        File newFilePath = new File(path);
        newFilePath.mkdirs();

        File newFile = new File (newFilePath, fileName);

        if (newFile.exists()){
            newFile.delete ();
        }

        FileOutputStream out = new FileOutputStream(newFile);
        out.write(data);
        out.flush();
        out.close();
        return newFile;

    }

    public static File saveFileToFolder(String path, String fileName, File f) throws IOException {
        File newFilePath = new File(path);
        newFilePath.mkdirs();

        return new File (newFilePath, fileName);
    }

    public static File saveBase64FileToFolder(String path, String fileName, String base64FileData) throws IOException {
        File newFilePath = new File(path);
        newFilePath.mkdirs();

        File newFile = new File (newFilePath, fileName);

        if (newFile.exists()){
            newFile.delete ();
        }

        byte[] decodedFile = Base64.decode(base64FileData, Base64.DEFAULT);

        FileOutputStream out = new FileOutputStream(newFile);
        out.write(decodedFile);
        out.flush();
        out.close();
        return newFile;
    }

    public static void moveFile(String origPath, String destPath, String name){
        InputStream inStream = null;
        OutputStream outStream = null;

        try{

            File afile =new File(origPath);
            File bfile =new File(destPath);
            bfile.mkdirs();

            File newFile = new File (destPath, name);

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(newFile);

            byte[] buffer = new byte[8024];

            int length;
            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            //delete the original file
            afile.delete();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static byte[] getFileByteArray(Context context, Uri uri) throws IOException {
        InputStream fileInputStream =  context.getContentResolver().openInputStream(uri);
        return FileManager.readBytes(fileInputStream);
    }

    public static File copyFileToFolder(Context context, Uri uri, String path, String fileNameChange) throws IOException {
        File newFilePath = new File(path);
        newFilePath.mkdirs();

        String fname = FileManager.getFileName(context, uri);
        if(fileNameChange != null){
            String fileExtension = FileManager.getFileExtension(fname);
            String finalFileName = fileNameChange;
            fname = new String(finalFileName);
        }

        File newFile = new File (newFilePath, fname);

        if (newFile.exists()){
            newFile.delete ();
            newFile = new File (newFilePath, fname);
        }

        FileOutputStream out = new FileOutputStream(newFile);
        InputStream fileInputStream =  context.getContentResolver().openInputStream(uri);
        byte[] fileByteArray = FileManager.readBytes(fileInputStream);
        out.write(fileByteArray);
        out.flush();
        out.close();
        return newFile;
    }

    public static void copyFile(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    public static Uri getUriFromIntentData(Intent data){
        Uri uri;
        if (data != null) {
            uri = data.getData();
            return uri;
        }
        return null;
    }

    private static byte[] readBytes(InputStream inputStream) throws IOException {
        // this dynamically extends to take the bytes you read
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the byteBuffer
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }

    public static String getFileName(Context context, Uri uri){
        String fileName = null;
        Cursor cursor = null;
        try {
            if(null != context.getContentResolver().query(uri, null, null, null, null)){
                cursor = context.getContentResolver().query(uri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        } catch (Throwable t){
            return null;
        }
        return fileName;
    }

    public static int getFileSize(Context context, Uri uri){
        int fileSize = 0;
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                fileSize = cursor.getInt(cursor.getColumnIndex(OpenableColumns.SIZE));
            }
        } finally {
            cursor.close();
        }

        return fileSize;
    }

    public static String getMimeType(Context context, Uri uri){
        return context.getContentResolver().getType(uri);
    }

    public static String getFileExtensionFromUri(Context context, Uri uri){
        final MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
    }

    public static String getBase64File(Context context, Uri uri) throws IOException {
        InputStream in =  context.getContentResolver().openInputStream(uri);
        byte[] arrby = FileManager.readBytes(in);
        return Base64.encodeToString(arrby, Base64.DEFAULT);
    }

    public static void removeFile(File file){
        if(file.exists()){
            if(file.isDirectory()){
                for(File child : file.listFiles()){
                    if(child.isDirectory()){
                        removeFile(child);
                        child.delete();
                    } else{
                        child.delete();
                    }
                }
            } else{
                file.delete();
            }

            if(file.exists())
                file.delete();
        }
    }

    public static Bitmap getBitmapPicture(Uri uri, Context context){
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
