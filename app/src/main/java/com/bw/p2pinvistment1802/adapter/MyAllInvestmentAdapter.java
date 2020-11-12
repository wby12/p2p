package com.bw.p2pinvistment1802.adapter;

import android.os.Handler;

import com.bw.p2pinvistment1802.R;
import com.bw.p2pinvistment1802.bean.AllInvestmentBean;
import com.bw.p2pinvistment1802.view.CircleProgressView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MyAllInvestmentAdapter extends BaseQuickAdapter<AllInvestmentBean.ResultBean, BaseViewHolder> {
    public MyAllInvestmentAdapter(int layoutResId, @Nullable List<AllInvestmentBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AllInvestmentBean.ResultBean resultBean) {

        baseViewHolder.setText(R.id.text_title,resultBean.getName());
        baseViewHolder.setText(R.id.text_money,resultBean.getMoney());
        baseViewHolder.setText(R.id.text_2,resultBean.getYearRate()+"%");
        baseViewHolder.setText(R.id.text_date,resultBean.getSuodingDays());
        baseViewHolder.setText(R.id.text_1,resultBean.getMinTouMoney());
        baseViewHolder.setText(R.id.text_3,resultBean.getMemberNum());

        CircleProgressView circleView = baseViewHolder.getView(R.id.progress_view);
       circleView.setProgress(Integer.parseInt(resultBean.getProgress()));
    }
}
