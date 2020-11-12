package com.bw.p2pinvistment1802.view.fragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.lib_core.view.BaseFragment;
import com.bw.p2pinvistment1802.R;
import com.bw.p2pinvistment1802.contract.HomeContract;
import com.bw.p2pinvistment1802.presenter.HomePresenter;
import com.bw.p2pinvistment1802.view.CircleProgressView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, OnBannerListener {

    private CircleProgressView progressView;
    private Banner banner;
    private int[] imageResource = new int[]{R.drawable.index01,R.drawable.index02,R.drawable.index03,R.drawable.index04};
    private List<Integer> imageList = new ArrayList<>();
    //轮播标题
    String[] mtitle = new String[]{"分享砍学费", "人脉总动员", "想不到你是这样的app", "购物节，爱不单行"};
    List<String> titleList = new ArrayList<>();


    @Override
    public int bandLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

        banner = (Banner) findViewById(R.id.banner);
        progressView = (CircleProgressView) findViewById(R.id.progress_view);

        initBanner();//轮播图

        progressView.setProgress(90);

    }

    /**
     * 轮播图
     */
    private void initBanner() {
        for (int i = 0; i < imageResource.length; i++) {
            imageList.add(imageResource[i]);//把图片资源循环放入list里面
            titleList.add(mtitle[i]);//把标题循环设置进列表里面
            //设置图片加载器，通过Glide加载图片
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            });
            //设置轮播的动画效果,里面有很多种特效,可以到GitHub上查看文档。
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imageList);//设置图片资源
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//设置banner显示样式（带标题的样式）
            banner.setBannerTitles(titleList); //设置标题集合（当banner样式有显示title时）
            //设置指示器位置（即图片下面的那个小圆点）
            banner.setIndicatorGravity(BannerConfig.CENTER);
            banner.setDelayTime(1000);//设置轮播时间3秒切换下一图
            banner.setOnBannerListener(this);//设置监听
            banner.start();//开始进行banner渲染
        }
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();//结束轮播
    }

    //对轮播图设置点击监听事件
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getContext(), "您点击了第"+(position+1)+"张图片", Toast.LENGTH_SHORT).show();
    }




}
