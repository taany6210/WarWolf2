package com.ty.warwolf2.presenter;

import android.support.v7.widget.RecyclerView;

import com.ty.warwolf2.base.BaseActivity;
import com.ty.warwolf2.base.BasePresenter;
import com.ty.warwolf2.base.LoadingPager;
import com.ty.warwolf2.config.AppConst;
import com.ty.warwolf2.model.bean.base.Today;
import com.ty.warwolf2.model.retrofit.RetrofitFactory;
import com.ty.warwolf2.model.retrofit.TFunc1;
import com.ty.warwolf2.model.retrofit.TSubscriber;
import com.ty.warwolf2.ui.adapter.HomeRvAdapter;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @ 文件名:   HomePresenter
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午3:40
 * @ 描述:    首页VM
 */

public class HomePresenter extends BasePresenter {

    public HomePresenter(LoadingPager pager, BaseActivity activity) {
        super(pager, activity);
    }


    public void start(RecyclerView recyclerView, PtrClassicFrameLayout ptrLayout) {
        RetrofitFactory.sApiService
                .getHistory(AppConst.APP_KEY, "11", "4")
                .map(new TFunc1<>(mPager))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new TSubscriber<List<Today>>(mPager) {
                    @Override
                    public void onDataSuccess(List<Today> todayList) {
                        mPager.onDataLoading(LoadingPager.LoadedResult.SUCCESS);
                        ptrLayout.refreshComplete();
                        //数据请求成功  接口回调回 view 处理数据更新
                        HomeRvAdapter homeRvAdapter = new HomeRvAdapter();
                        recyclerView.setAdapter(homeRvAdapter);
                        homeRvAdapter.setList(todayList);
                    }

                    @Override
                    public void onDataError(String message) {

                    }
                });
    }

}
