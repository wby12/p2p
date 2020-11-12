package com.bw.p2pinvistment1802.view.activity;

import android.content.Intent;
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

import com.bw.lib_core.view.BaseActivity;
import com.bw.p2pinvistment1802.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageBack;
    private EditText editName;
    private EditText editPwd;
    private Button btLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        imageBack = (ImageView) findViewById(R.id.image_back);
        editName = (EditText) findViewById(R.id.edit_name);
        editPwd = (EditText) findViewById(R.id.edit_pwd);
        btLogin = (Button) findViewById(R.id.bt_login);

        btLogin.setOnClickListener(this);
        imageBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                login();
                break;

            case R.id.image_back:
                finish();
                break;
        }
    }

    private void login() {

        String username = editName.getText().toString();
        String pwd = editPwd.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)){
                    EMClient.getInstance().login(username,pwd,new EMCallBack() {//回调
                        @Override
                        public void onSuccess() {
                            EMClient.getInstance().groupManager().loadAllGroups();
                            EMClient.getInstance().chatManager().loadAllConversations();
                            Log.d("main", "登录聊天服务器成功！");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            });

                        }

                        @Override
                        public void onProgress(int progress, String status) {

                        }

                        @Override
                        public void onError(int code, String message) {
                            Log.d("main", "登录聊天服务器失败！");
                        }
                    });
                }

            }
        }).start();


    }
}
