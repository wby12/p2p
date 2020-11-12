package com.bw.p2pinvistment1802.api;

import com.bw.p2pinvistment1802.bean.AllInvestmentBean;
import com.bw.p2pinvistment1802.bean.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyApi {
    /**
     * 获取更新应用版本信息数据
     * @return
     */
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> get_version();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<AllInvestmentBean> get_investment();
}
