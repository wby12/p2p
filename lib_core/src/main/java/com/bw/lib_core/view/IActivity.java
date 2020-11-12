package com.bw.lib_core.view;


import androidx.annotation.LayoutRes;

public interface IActivity {

    @LayoutRes
    int bandLayout();

    void initView();
    void initInject();
    void initData();
}
