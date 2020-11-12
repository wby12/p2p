package com.bw.p2pinvistment1802.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.lib_core.view.BaseActivity;
import com.bw.p2pinvistment1802.R;
import com.bw.p2pinvistment1802.contract.HomeContract;
import com.bw.p2pinvistment1802.presenter.HomePresenter;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import javax.xml.transform.Result;

import static com.wildma.pictureselector.PictureSelector.PICTURE_RESULT;
import static com.wildma.pictureselector.PictureSelector.SELECT_REQUEST_CODE;

public class UserMessageActivity extends BaseActivity<HomePresenter> implements HomeContract.View, View.OnClickListener {

    private ImageView imageBack;
    private ImageView imageTitle;
    private TextView updateImage;
    private Button exitLogin;

    @Override
    public int bandLayout() {
        return R.layout.activity_user_message;
    }

    @Override
    public void initView() {

        imageBack = (ImageView) findViewById(R.id.image_back);
        imageTitle = (ImageView) findViewById(R.id.image_title);
        updateImage = (TextView) findViewById(R.id.update_image);
        exitLogin = (Button) findViewById(R.id.exit_login);

        imageBack.setOnClickListener(this);
        imageTitle.setOnClickListener(this);
        updateImage.setOnClickListener(this);
        exitLogin.setOnClickListener(this

        );

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
            case R.id.image_back:
                finish();
                break;

            case R.id.update_image:
                PictureSelector.create(this, SELECT_REQUEST_CODE).selectPicture();
                break;

            case R.id.exit_login:
                finish();
                break;
        }
    }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_REQUEST_CODE && data!=null){
            PictureBean pictureBean = (PictureBean) data.getExtras().get(PICTURE_RESULT);
            if(pictureBean!=null && pictureBean.getPath()!=null){
                Glide.with(this)
                        .load(pictureBean.getPath())
                        .apply(new RequestOptions().circleCrop())
                        .into(imageTitle);
            }
        }
    }
}
