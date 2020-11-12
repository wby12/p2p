package com.bw.p2pinvistment1802.contract;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.model.IModel;
import com.bw.lib_core.view.IView;
import com.bw.p2pinvistment1802.bean.VersionBean;

public interface VersionContract {
    interface View extends IView{
        void initAdapter(VersionBean versionBean);
    }
    interface Model extends IModel{
        void request_Version(BaseObserver<VersionBean> observer);
    }
}
