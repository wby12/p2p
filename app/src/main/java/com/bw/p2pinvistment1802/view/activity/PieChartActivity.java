package com.bw.p2pinvistment1802.view.activity;

import com.bw.lib_core.view.BaseActivity;
import com.bw.p2pinvistment1802.R;
import com.bw.p2pinvistment1802.presenter.InvestmentPresenter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;

public class PieChartActivity extends BaseActivity<InvestmentPresenter> {

    private PieChartUtils chartUtil;
    private PieChart mChart;
    @Override
    public int bandLayout() {
        return R.layout.activity_pie_chart;
    }

    @Override
    public void initView() {

        mChart = (PieChart) findViewById(R.id.mPieChart);
        chartUtil = new PieChartUtils(this);
        PieData mPieData =chartUtil.getPieData(3, 100);
        chartUtil.showChart(mChart, mPieData);

    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }
}
