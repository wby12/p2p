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

public class CashActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etInputMoney;
    private Button btnTixian;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        initView();
    }

    private void initView() {
        etInputMoney = (EditText) findViewById(R.id.et_input_money);
        btnTixian = (Button) findViewById(R.id.btn_tixian);
        btnTixian.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tixian:
                String text = etInputMoney.getText().toString();
                Toast.makeText(this, text+"正在提现，请稍后...", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
