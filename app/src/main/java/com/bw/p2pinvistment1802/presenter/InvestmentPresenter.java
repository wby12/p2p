package com.bw.p2pinvistment1802.presenter;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.presenter.BasePresenter;
import com.bw.p2pinvistment1802.bean.AllInvestmentBean;
import com.bw.p2pinvistment1802.contract.HomeContract;
import com.bw.p2pinvistment1802.contract.InvestmentContract;

public class InvestmentPresenter extends BasePresenter<InvestmentContract.Model,InvestmentContract.View> {
    public InvestmentPresenter(InvestmentContract.Model mModel, InvestmentContract.View mView) {
        super(mModel, mView);
    }
    public void get_investment(){

        mModel.request_investment(new BaseObserver<AllInvestmentBean>() {
            @Override
            public void success(AllInvestmentBean mybean) {
                mView.initAdapter(mybean);
            }
        });
    }
}
