package com.bw.p2pinvistment1802.model;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.http.HttpRetrofitManager;
import com.bw.lib_core.model.BaseModel;
import com.bw.p2pinvistment1802.api.MyApi;
import com.bw.p2pinvistment1802.bean.VersionBean;
import com.bw.p2pinvistment1802.contract.VersionContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VersionModel extends BaseModel implements VersionContract.Model {
    @Override
    public void request_Version(BaseObserver<VersionBean> observer) {
        HttpRetrofitManager.getInstance().getRetrofit()
                .create(MyApi.class)
                .get_version()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
