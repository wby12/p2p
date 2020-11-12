package com.bw.p2pinvistment1802.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.bw.lib_core.view.BaseFragment;
import com.bw.p2pinvistment1802.R;
import com.bw.p2pinvistment1802.contract.HomeContract;
import com.bw.p2pinvistment1802.presenter.HomePresenter;
import com.bw.p2pinvistment1802.view.activity.GesturePasswordActivity;
import com.bw.p2pinvistment1802.view.activity.GuiGuActivity;
import com.bw.p2pinvistment1802.view.activity.RegisterActivity;
import com.hyphenate.chat.EMClient;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

public class MoreFragment extends BaseFragment<HomePresenter> implements HomeContract.View, View.OnClickListener {

    private TextView textRegister;
    private Switch btSwitch;
    private TextView textResetPwd;
    private TextView callPhone;
    private TextView textUser;
    private TextView textShare;
    private TextView textBottom;

    @Override
    public int bandLayout() {
        return R.layout.fragment_more;
    }

    @Override
    public void initView() {

        textRegister = (TextView) findViewById(R.id.text_register);
        btSwitch = (Switch) findViewById(R.id.bt_switch);
        textResetPwd = (TextView) findViewById(R.id.text_resetPwd);
        callPhone = (TextView) findViewById(R.id.call_phone);
        textUser = (TextView) findViewById(R.id.text_user);
        textShare = (TextView) findViewById(R.id.text_share);
        textBottom = (TextView) findViewById(R.id.text_bottom);

        textRegister.setOnClickListener(this);
        textBottom.setOnClickListener(this);
        btSwitch.setOnClickListener(this);
        textResetPwd.setOnClickListener(this);
        callPhone.setOnClickListener(this);
        textUser.setOnClickListener(this);
        textShare.setOnClickListener(this);
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
            case R.id.text_register:
                initRegister();
                break;

            case R.id.text_bottom:
                initGuiGu();
                break;

            case R.id.bt_switch:
                init_switch();
                break;

            case R.id.text_resetPwd:
                init_text_resetPwd();
                break;

            case R.id.call_phone:
                init_call();
                break;

            case R.id.text_user:
                init_user_result();
                break;

            case R.id.text_share:
                initShare();
                break;
        }
    }

    /**
     * 重置手势密码
     */
    private void init_text_resetPwd() {
        final PopupWindow popupWindow = new PopupWindow();
        popupWindow.setHeight(700);
        popupWindow.setWidth(1100);
        popupWindow.setOutsideTouchable(true);
        View inflate = getLayoutInflater().inflate(R.layout.layout_password_item, null);
        popupWindow.setContentView(inflate);
        Button bt_cancle = inflate.findViewById(R.id.bt_cancle);
        Button bt_sure = inflate.findViewById(R.id.bt_sure);
        popupWindow.showAsDropDown(callPhone);

        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "您取消了设置手势密码", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();

            }
        });

        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "设置手势密码,请稍后...", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                Intent intent = new Intent(getContext(), GesturePasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 联系人
     */
    private void init_call() {
        callPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupWindow popupWindow = new PopupWindow();
                popupWindow.setHeight(700);
                popupWindow.setWidth(1100);
                popupWindow.setOutsideTouchable(true);
                View inflate = getLayoutInflater().inflate(R.layout.layout_call_phone, null);
                popupWindow.setContentView(inflate);
                Button bt_cancle = inflate.findViewById(R.id.bt_cancle);
                Button bt_sure = inflate.findViewById(R.id.bt_sure);
                popupWindow.showAsDropDown(callPhone);

                bt_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "您取消了打电话", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });

                bt_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "打电话给客服,请稍等...", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    /**
     * 手势密码
     */
    private void init_switch() {
        btSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    btSwitch.getTextOn();
                    final PopupWindow popupWindow = new PopupWindow();
                    popupWindow.setHeight(700);
                    popupWindow.setWidth(1100);
                    popupWindow.setOutsideTouchable(true);
                    View inflate = getLayoutInflater().inflate(R.layout.layout_password_item, null);
                    popupWindow.setContentView(inflate);
                    Button bt_cancle = inflate.findViewById(R.id.bt_cancle);
                    Button bt_sure = inflate.findViewById(R.id.bt_sure);
                    popupWindow.showAsDropDown(callPhone);

                    bt_cancle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "您取消了设置手势密码", Toast.LENGTH_SHORT).show();
                            popupWindow.dismiss();

                        }
                    });

                    bt_sure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "设置手势密码,请稍后...", Toast.LENGTH_SHORT).show();
                            popupWindow.dismiss();
                            Intent intent = new Intent(getContext(), GesturePasswordActivity.class);
                            startActivity(intent);
                        }
                    });
                }else {
                    btSwitch.getTextOff();
                    Toast.makeText(getContext(), "您取消了设置手势密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 用户反馈
     */
    private void init_user_result() {
        textUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupWindow popupWindow = new PopupWindow();
                popupWindow.setHeight(900);
                popupWindow.setWidth(1200);
                popupWindow.setOutsideTouchable(true);
                View inflate = getLayoutInflater().inflate(R.layout.layout_user_result, null);
                popupWindow.setContentView(inflate);
                Button bt_cancle = inflate.findViewById(R.id.bt_cancle);
                Button bt_sure = inflate.findViewById(R.id.bt_sure);
                popupWindow.showAsDropDown(callPhone);

                bt_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        popupWindow.dismiss();
                    }
                });

                bt_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    /**
     * 友盟分享
     */
    private void initShare() {
        UMImage image = new UMImage(getActivity(), R.drawable.umeng_socialize_share_web);//资源文件
        new ShareAction(getActivity()).withText("hello").withMedia(image).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener).open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(getContext(),"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(),"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),"取消了",Toast.LENGTH_LONG).show();

        }
    };

    private void initGuiGu() {
        Intent intent = new Intent(getActivity(), GuiGuActivity.class);
        startActivity(intent);
    }

    private void initRegister() {

        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivity(intent);

    }
}

