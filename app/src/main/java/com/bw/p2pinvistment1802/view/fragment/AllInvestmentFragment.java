package com.bw.p2pinvistment1802.view.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.lib_core.view.BaseFragment;
import com.bw.lib_core.view.IFragment;
import com.bw.lib_core.view.IView;
import com.bw.p2pinvistment1802.R;
import com.bw.p2pinvistment1802.adapter.MyAllInvestmentAdapter;
import com.bw.p2pinvistment1802.bean.AllInvestmentBean;
import com.bw.p2pinvistment1802.contract.InvestmentContract;
import com.bw.p2pinvistment1802.model.InvestmentModel;
import com.bw.p2pinvistment1802.presenter.InvestmentPresenter;

import java.util.List;

public class AllInvestmentFragment extends BaseFragment<InvestmentPresenter> implements InvestmentContract.View {

    private RecyclerView recyclerView;
    private MyAllInvestmentAdapter myAllInvestmentAdapter;

    @Override
    public int bandLayout() {
        return R.layout.fragment_all_investment;
    }

    @Override
    public void initView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myAllInvestmentAdapter = new MyAllInvestmentAdapter(R.layout.layout_iinvestment_tem,null);
        recyclerView.setAdapter(myAllInvestmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void initInject() {

        mPresenter = new InvestmentPresenter(new InvestmentModel(),this);
    }

    @Override
    public void initData() {

        mPresenter.get_investment();
    }

    @Override
    public void initAdapter(AllInvestmentBean allInvestmentBean) {

        List<AllInvestmentBean.ResultBean> data = myAllInvestmentAdapter.getData();
        data.addAll(allInvestmentBean.getResult());
        myAllInvestmentAdapter.notifyDataSetChanged();
    }
}
