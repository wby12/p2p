package com.bw.p2pinvistment1802.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bw.p2pinvistment1802.R;

public class RechargeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageBack;
    private TextView chongzhiText;
    private View view;
    private EditText etChongzhi;
    private TextView chongzhiText2;
    private TextView yueTv;
    private Button btnChongzhi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        initView();
    }

    private void initView() {
        imageBack = (ImageView) findViewById(R.id.image_back);
        chongzhiText = (TextView) findViewById(R.id.chongzhi_text);
        view = (View) findViewById(R.id.view);
        etChongzhi = (EditText) findViewById(R.id.et_chongzhi);
        chongzhiText2 = (TextView) findViewById(R.id.chongzhi_text2);
        yueTv = (TextView) findViewById(R.id.yue_tv);
        btnChongzhi = (Button) findViewById(R.id.btn_chongzhi);

        imageBack.setOnClickListener(this);
        btnChongzhi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;

            case R.id.btn_chongzhi:
                String text = etChongzhi.getText().toString();
                Toast.makeText(this, text+"正在充值，请稍后...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
