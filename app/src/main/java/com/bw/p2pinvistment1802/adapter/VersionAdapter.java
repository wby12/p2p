package com.bw.p2pinvistment1802.adapter;

import com.bw.p2pinvistment1802.bean.VersionBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VersionAdapter extends BaseQuickAdapter<VersionBean.ResultBean, BaseViewHolder> {
    public VersionAdapter(int layoutResId, @Nullable List<VersionBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, VersionBean.ResultBean resultBean) {

    }
}
