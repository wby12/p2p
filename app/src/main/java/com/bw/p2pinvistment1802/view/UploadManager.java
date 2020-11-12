package com.bw.p2pinvistment1802.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 获取版本号
 */
public class UploadManager {

    private String saveFilePath = "/sdcard/updateApk/";//保存apk的路径
    private String saveFileName = saveFilePath+"apkName.apk";
    private Context context;
    private boolean cancleFlag = false;//停止下载的标志
    public UploadManager(Context mContext){

        this.context = mContext;
    }

    /**
     * 获取当前的版本号
     */

    public long gettAppVersionCode(){
        long versionCode = 0;

        try {
            PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionCode;
    }

    /**
     * 获取当前的版本名称
     */

    public String gettAppVersionName(){
        String versionName = "";

        try {
            PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }

    /**
     * 安装APK方法
     */
    public void installAPK(){

        File file = new File(saveFileName);
        if(!file.exists()){
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://"+file.toString()),"application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 下载APK
     */
    public void downLoadAPK(final String apkUrl){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(apkUrl);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    //int length=urlConnection.getContentLength();
                    InputStream inputStream = urlConnection.getInputStream();
                    File file = new File(saveFilePath);
                    if(!file.exists()){
                        file.mkdir();
                    }
                    String apkFile_name = saveFileName;
                    File apkFile = new File(apkFile_name);
                    FileOutputStream fileOutputStream = new FileOutputStream(apkFile);
                    int count = 0;
                    byte[] bytes = new byte[1024];
                     do {
                         int read = inputStream.read(bytes);
                         count += read;
                         //更新进度条handler传值
                         Message message = new Message();
                         message.obj = read;
                         message.what = 1;
                         handler.sendMessage(message);

                         if(read<=0){
                             //下载完成
                             handler.sendEmptyMessage(0);
                         }
                         fileOutputStream.write(bytes,0,count);
                     }while (!cancleFlag);
                     fileOutputStream.close();
                     inputStream.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 0:
                    installAPK();
                    break;

                case 1:
                    //更新进度条
                    break;
            }
        }
    };
}
