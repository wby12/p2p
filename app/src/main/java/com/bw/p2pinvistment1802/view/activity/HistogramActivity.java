package com.bw.p2pinvistment1802.view.activity;

import com.bw.lib_core.view.BaseActivity;
import com.bw.p2pinvistment1802.R;
import com.bw.p2pinvistment1802.presenter.InvestmentPresenter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

/**
 * 柱状图
 */
public class HistogramActivity extends BaseActivity<InvestmentPresenter> {

    private BarChart barChart;
    private BarCharts mBarChart3;

    @Override
    public int bandLayout() {
        return R.layout.activity_histogram;
    }

    @Override
    public void initView() {

        barChart = (BarChart) findViewById(R.id.barChart);
        mBarChart3 = new BarCharts(barChart);
        BarData data = new BarData(mBarChart3.getXAxisValues(),mBarChart3.getDataSet());
        // 设置数据
        barChart.setData(data);

    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }
}
