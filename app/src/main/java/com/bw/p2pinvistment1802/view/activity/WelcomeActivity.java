package com.bw.p2pinvistment1802.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.bw.p2pinvistment1802.R;
import com.bw.p2pinvistment1802.view.UpdateApkView;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private RelativeLayout welcomeImage;
    private TextView textVersion;
    private float num = 0.6f;
    private int number = 3;//倒计时
    private  UpdateApkView updateApkView;
    private TextView textTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();

    }



   // private Timer timer;
    private void initView() {

        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
        welcomeImage = (RelativeLayout) findViewById(R.id.welcome_image);
        textVersion = (TextView) findViewById(R.id.text_version);
        textTime = (TextView) findViewById(R.id.text_time);

//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                if (number <= 0) {
//                  //  updateApkView.upDataDownload("http://10.161.9.87/banben/gengxin.json");
//                    timer.cancel();
//                }
//                num += 0.1f;
//                number--;
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //渐变
//                        welcomeImage.setAlpha(num);
//                    }
//                });
//
//            }
//        }, 0, 1000);

        updateApkView = new UpdateApkView(this);
        updateApkView.showDialog("http://49.233.93.155:8080/atguigu/apk/P2PInvest/app_new.apk");
        String appVersionName = updateApkView.getAppVersionName();
        textVersion.setText(appVersionName);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //timer.cancel();
    }

}
