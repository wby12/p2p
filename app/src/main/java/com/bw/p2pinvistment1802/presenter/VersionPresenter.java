package com.bw.p2pinvistment1802.presenter;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.presenter.BasePresenter;
import com.bw.p2pinvistment1802.bean.VersionBean;
import com.bw.p2pinvistment1802.contract.VersionContract;

public class VersionPresenter extends BasePresenter<VersionContract.Model,VersionContract.View> {
    public VersionPresenter(VersionContract.Model mModel, VersionContract.View mView) {
        super(mModel, mView);
    }

    public void get_version(){
        mModel.request_Version(new BaseObserver<VersionBean>() {
            @Override
            public void success(VersionBean mybean) {
                mView.initAdapter(mybean);
            }
        });
    }
}
