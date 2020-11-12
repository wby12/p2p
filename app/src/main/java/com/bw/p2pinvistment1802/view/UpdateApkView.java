package com.bw.p2pinvistment1802.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import com.bw.p2pinvistment1802.R;
import com.bw.p2pinvistment1802.view.activity.MainActivity;
import com.bw.p2pinvistment1802.view.activity.WelcomeActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class UpdateApkView {

    private TextView barSum;
    private TextView barNum;
    private ProgressBar upLodeBar;
    //文件路径
    private Context context;
    private String saveFilePath="/sdcard/upDataApk/";//保存apk到SDcard下面
    private String saveFileName=saveFilePath+"apkName.apk";//下载完成的文件名字

    private int number = 5;

    public UpdateApkView(Context context) {
        this.context = context;
    }

    /*
        handel更新进度条
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1:
                    int a = (int) msg.obj;
                    upLodeBar.setProgress(a);
                    barNum.setText("当前进度:"+a);
                    int max = upLodeBar.getMax();
                    barSum.setText("总进度"+max);
                    break;
                case 2:
                    IsInstallAPK();
                    break;

            }

        }
    };


    /**
     * 是否安装
     */
    private void IsInstallAPK(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("安装");
        builder.setMessage("是否安装");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                installAPK();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
        builder.show();
    }


    /**
     *  下载文件
     * @param url
     */
    public void upDataDownload(final String url){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_update_item, null);

        upLodeBar = inflate.findViewById(R.id.seekbar);
        barSum = inflate.findViewById(R.id.text_sum);
        barNum = inflate.findViewById(R.id.text_num);

        builder.setView(inflate);
        builder.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url1 = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                    connection.connect();//链接服务器
                    //判断是否链接成功
                    if (connection.getResponseCode() == 200){
                        InputStream inputStream = connection.getInputStream();
                        //获取文件总大小
                        int contentLength = connection.getContentLength();
                        upLodeBar.setMax(contentLength);
                        File file = new File(saveFilePath);
                        if (!file.exists()){
                            file.mkdir();
                        }
                        String apkFile = saveFileName;
                        File file1 = new File(apkFile);
                        int len = 0;
                        int sum = 0;//当前进度
                        byte[] bys = new byte[1024];
                        FileOutputStream fileOutputStream = new FileOutputStream(file1);
                        StringBuffer stringBuffer = new StringBuffer();
                        while((len = inputStream.read(bys))!=-1){
                            sum+=len;
                            fileOutputStream.write(len);
                            String a = new String(bys,0,len);
                            stringBuffer.append(a);
                            Message message = new Message();
                            message.what = 1;
                            message.obj = sum;
                            handler.sendMessage(message);
                        }
                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 是否更新
     * @param apkUrl
     */
    public void showDialog(final String apkUrl){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("有新版本是否更新");
        builder.setTitle("更新");
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                upDataDownload(apkUrl);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //initTimer();
                Intent intent = new Intent(context,MainActivity.class);
                context.startActivity(intent);
            }
        });
        builder.show();
    }

    private void initTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(number>0){
                    number--;
                    Log.i("wby", "run: "+number);
                }else {
                    Intent intent = new Intent(context,MainActivity.class);
                    context.startActivity(intent);
                    timer.cancel();
                }
            }
        },0,1000);
    }

    /**
     * 获取当前版本号
     * @return
     */
    public int getAppVersionCode(){
        int appCde = 0;
        try {
            PackageInfo activityInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
            appCde = activityInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return appCde;
    }


    /**
     * 获取当前版本名称
     * @return
     */
    public String getAppVersionName(){
        String appName = "";
        try {
            PackageInfo activityInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
            appName = activityInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return appName;
    }

    /**
     *  安装apk
     */
    public void installAPK(){
        File apkFile = new File(saveFileName);
        if (!apkFile.exists()){
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //安装完成后，启动app
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.parse("file://" + apkFile.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

}
