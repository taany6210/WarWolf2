package com.ty.warwolf2.base;


import com.dgrlucky.log.LogX;
import com.ty.warwolf2.config.ConstZh;
import com.ty.warwolf2.util.NetworkUtil;

import rx.Subscription;

/**
 * @ 文件名:   BasePresenter
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:57
 * @ 描述:
 */

public class BasePresenter {
    protected LoadingPager mPager;
    protected BaseActivity mActivity;

    public BasePresenter(LoadingPager pager, BaseActivity activity) {
        this.mPager = pager;
        this.mActivity = activity;
        checkNetwork();
    }

    public void checkNetwork() {
        if (!NetworkUtil.isAvailable(App.sContext)) {
            LogX.e(ConstZh.NO_NETWORK);
            mPager.onDataLoading(LoadingPager.LoadedResult.SUCCESS);
            return;
        }
    }

    /**
     * 添加订阅
     *
     * @param s 需要取消的Subscriptions
     */
    protected void addSubscribe(Subscription s) {
        mActivity.addSubscribe(s);
    }



}
