package com.bw.p2pinvistment1802.model;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.http.HttpRetrofitManager;
import com.bw.lib_core.model.BaseModel;
import com.bw.p2pinvistment1802.api.MyApi;
import com.bw.p2pinvistment1802.bean.AllInvestmentBean;
import com.bw.p2pinvistment1802.contract.InvestmentContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InvestmentModel extends BaseModel implements InvestmentContract.Model {
    @Override
    public void request_investment(BaseObserver<AllInvestmentBean> observer) {
        HttpRetrofitManager.getInstance().getRetrofit()
                .create(MyApi.class)
                .get_investment()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
