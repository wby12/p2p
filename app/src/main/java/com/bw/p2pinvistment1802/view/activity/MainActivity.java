package com.bw.p2pinvistment1802.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bw.lib_core.view.BaseActivity;
import com.bw.p2pinvistment1802.R;
import com.bw.p2pinvistment1802.presenter.HomePresenter;
import com.bw.p2pinvistment1802.view.fragment.HomeFragment;
import com.bw.p2pinvistment1802.view.fragment.InvestmentFragment;
import com.bw.p2pinvistment1802.view.fragment.MoreFragment;
import com.bw.p2pinvistment1802.view.fragment.MyAssetsFragment;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends BaseActivity<HomePresenter> {

    private LinearLayout linear;
    private EasyNavigationBar easyBars;
    private String[] stringsList = {"首页","投资","我的资产","更多"};
    private int[] unSelectList = {R.drawable.bottom01,R.drawable.bottom03,R.drawable.bottom05,R.drawable.bottom07};
    private int[] selectList = {R.drawable.bottom02,R.drawable.bottom04,R.drawable.bottom06,R.drawable.bottom08};
    private List<Fragment> fragmentList = new ArrayList<>();

    private long firsdtTime = 0;

    @Override
    public int bandLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        easyBars = (EasyNavigationBar) findViewById(R.id.easy_bar);
        linear = (LinearLayout) findViewById(R.id.linear);

        initBar();//底部导航
    }

    /**
     * 底部导航
     */
    private void initBar() {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new InvestmentFragment());
        fragmentList.add(new MyAssetsFragment());
        fragmentList.add(new MoreFragment());

        easyBars.titleItems(stringsList)
                .selectIconItems(selectList)
                .normalIconItems(unSelectList)
                .fragmentManager(getSupportFragmentManager())
                .fragmentList(fragmentList)
                .selectTextColor(Color.parseColor("#3984D1"))
                .normalTextColor(Color.parseColor("#969696"))
                .mode(EasyNavigationBar.NavigationMode.MODE_NORMAL)
                .canScroll(true)
                .build();

        easyBars.setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {
            @Override
            public boolean onTabSelectEvent(View view, int position) {

                if(position==2){
                    PopupWindow popupWindow = new PopupWindow();
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setHeight(600);
                    popupWindow.setWidth(1000);
                    View inflate = getLayoutInflater().inflate(R.layout.layout_my_more, null);
                    popupWindow.setContentView(inflate);
                    Button button = inflate.findViewById(R.id.bt_sure);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                            popupWindow.dismiss();
                        }
                    });
                    popupWindow.showAtLocation(linear, Gravity.CENTER,0,0);

                }
                return false;
            }

            @Override
            public boolean onTabReSelectEvent(View view, int position) {
                return false;
            }
        });

    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }

    //双击退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long time = System.currentTimeMillis();
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if(time-firsdtTime>2000){
                firsdtTime=time;
                Toast.makeText(this, "再点击一次，退出当前应用", Toast.LENGTH_SHORT).show();
                return true;
            }else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
