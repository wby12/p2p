package com.bw.p2pinvistment1802.view.fragment;

import android.graphics.Color;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bw.lib_core.view.BaseFragment;
import com.bw.p2pinvistment1802.R;
import com.bw.p2pinvistment1802.adapter.MyFragmentAdapter;
import com.bw.p2pinvistment1802.contract.HomeContract;
import com.bw.p2pinvistment1802.presenter.HomePresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class InvestmentFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    private TabLayout tabLayout;
    private ViewPager viewPage;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    private MyFragmentAdapter myFragmentAdapter;

    @Override
    public int bandLayout() {
        return R.layout.fragment_investment;
    }

    @Override
    public void initView() {

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPage = (ViewPager) findViewById(R.id.viewPage);

        fragmentList.add(new AllInvestmentFragment());
        fragmentList.add(new CommonInvestmentFragment());
        fragmentList.add(new HotInvestmentFragment());

        stringList.add("全部理财");
        stringList.add("推荐理财");
        stringList.add("热门理财");

        myFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(),fragmentList,stringList);
        viewPage.setAdapter(myFragmentAdapter);
        tabLayout.setupWithViewPager(viewPage);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#29B8EE"));
        tabLayout.setSelectedTabIndicatorHeight(300);

    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }
}
