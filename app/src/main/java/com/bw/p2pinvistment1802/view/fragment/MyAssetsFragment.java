package com.bw.p2pinvistment1802.view.fragment;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bw.lib_core.view.BaseFragment;
import com.bw.p2pinvistment1802.R;
import com.bw.p2pinvistment1802.contract.HomeContract;
import com.bw.p2pinvistment1802.presenter.HomePresenter;
import com.bw.p2pinvistment1802.view.activity.CashActivity;
import com.bw.p2pinvistment1802.view.activity.HistogramActivity;
import com.bw.p2pinvistment1802.view.activity.LineChartActivity;
import com.bw.p2pinvistment1802.view.activity.LoginActivity;
import com.bw.p2pinvistment1802.view.activity.PieChartActivity;
import com.bw.p2pinvistment1802.view.activity.RechargeActivity;
import com.bw.p2pinvistment1802.view.activity.UserMessageActivity;

public class MyAssetsFragment extends BaseFragment<HomePresenter> implements HomeContract.View, View.OnClickListener {

    private ImageView settings;
    private TextView btLogin;
    private LinearLayout linear;
    private Button btCz;
    private Button btTx;
    private ImageView money;
    private TextView textTzgl;
    private ImageView reward;
    private TextView textTzglZg;
    private ImageView asset;
    private TextView textZcgl;

    @Override
    public int bandLayout() {
        return R.layout.fragment_my_assets;
    }

    @Override
    public void initView() {

        settings = (ImageView) findViewById(R.id.settings);
        btLogin = (TextView) findViewById(R.id.bt_login);
        linear = (LinearLayout) findViewById(R.id.linear);
        btCz = (Button) findViewById(R.id.bt_cz);
        btTx = (Button) findViewById(R.id.bt_tx);
        money = (ImageView) findViewById(R.id.money);
        textTzgl = (TextView) findViewById(R.id.text_tzgl);
        reward = (ImageView) findViewById(R.id.reward);
        textTzglZg = (TextView) findViewById(R.id.text_tzgl_zg);
        asset = (ImageView) findViewById(R.id.asset);
        textZcgl = (TextView) findViewById(R.id.text_zcgl);

        btLogin.setOnClickListener(this);
        settings.setOnClickListener(this);
        btCz.setOnClickListener(this);
        btTx.setOnClickListener(this);
        textTzgl.setOnClickListener(this);
        textTzglZg.setOnClickListener(this);
        textZcgl.setOnClickListener(this);

    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                login();
                break;

            case R.id.settings:
                user_message();
                break;

            case R.id.bt_cz:
                init_cz();
                break;

            case R.id.bt_tx:
                init_tx();
                break;

            case R.id.text_tzgl:
                init_tzgl();
                break;

            case R.id.text_tzgl_zg:
                init_tzgl_zg();
                break;

            case R.id.text_zcgl:
                init_zcgl();
                break;
        }
    }

    /**
     * 饼图
     */
    private void init_zcgl() {
        Intent intent = new Intent(getContext(), PieChartActivity.class);
        startActivity(intent);
    }

    /**
     * 柱状图
     */
    private void init_tzgl_zg() {

        Intent intent = new Intent(getContext(), HistogramActivity.class);
        startActivity(intent);
    }

    /**
     *折线图
     */
    private void init_tzgl() {
        Intent intent = new Intent(getContext(), LineChartActivity.class);
        startActivity(intent);
    }

    private void init_tx() {
        Intent intent = new Intent(getContext(), CashActivity.class);
        startActivity(intent);
    }

    /**
     * 充值
     *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       t'g't'g't'g't'g't'g't'g't'g't'g't'g't
     *
     */
    private void init_cz() {
        Intent intent = new Intent(getContext(), RechargeActivity.class);
        startActivity(intent);
    }

    private void user_message() {
        Intent intent = new Intent(getContext(), UserMessageActivity.class);
        startActivity(intent);
    }

    private void login() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}
