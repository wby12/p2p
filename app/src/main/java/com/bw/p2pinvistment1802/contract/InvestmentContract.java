package com.bw.p2pinvistment1802.contract;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.model.IModel;
import com.bw.lib_core.view.IView;
import com.bw.p2pinvistment1802.bean.AllInvestmentBean;

public interface InvestmentContract {

    interface View extends IView{
        void initAdapter(AllInvestmentBean allInvestmentBean);
    }

    interface Model extends IModel{
        void request_investment(BaseObserver<AllInvestmentBean> observer);
    }
}
