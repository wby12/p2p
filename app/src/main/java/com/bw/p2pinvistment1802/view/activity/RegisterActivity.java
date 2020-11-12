package com.bw.p2pinvistment1802.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bw.p2pinvistment1802.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageBack;
    private EditText editName;
    private EditText editPwd;
    private Button btRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        imageBack = (ImageView) findViewById(R.id.image_back);
        editName = (EditText) findViewById(R.id.edit_name);
        editPwd = (EditText) findViewById(R.id.edit_pwd);
        btRegister = (Button) findViewById(R.id.bt_register);

        btRegister.setOnClickListener(this);
        imageBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_register:
                initRegister();
                break;

            case R.id.image_back:
                finish();
                break;
        }
    }

    private void initRegister() {
        String username = editName.getText().toString();
        String pwd = editPwd.getText().toString();
        //注册失败会抛出HyphenateException
         new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     if (!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(pwd)) {


                         EMClient.getInstance().createAccount(username, pwd);//同步方法

                         Log.i("wby", "initRegister: "+username+pwd);
                         runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                             }
                         });

                     }else {
                         runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                             }
                         });

                     }

                 } catch (HyphenateException e) {
                     e.printStackTrace();
                 }
             }
         }).start();

    }
}
