package com.ty.warwolf2.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ty.warwolf2.R;
import com.ty.warwolf2.base.BaseFragment;
import com.ty.warwolf2.presenter.HomePresenter;
import com.ty.warwolf2.util.AppBasicSetUtil;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * @ 文件名:   HomeFragment
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午3:12
 * @ 描述:    首页
 */

public class HomeFragment extends BaseFragment{

    private HomePresenter mViewModel;
    private RecyclerView mRecyclerView;
    private PtrClassicFrameLayout mPtrLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPtrLayout = ((PtrClassicFrameLayout) mView.findViewById(R.id.ptr_layout));
        mRecyclerView = ((RecyclerView) mView.findViewById(R.id.recycler_view));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setHasFixedSize(true);

        AppBasicSetUtil.setRefreshHeader(mPtrLayout, mActivity);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mViewModel = new HomePresenter(mPager, mActivity);
        mViewModel.start(mRecyclerView,mPtrLayout);
    }

    @Override
    protected void reloadData() {
        mViewModel.start(mRecyclerView, mPtrLayout);
    }


    @Override
    protected void initListener() {
        mPtrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecyclerView,header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mViewModel.start(mRecyclerView, mPtrLayout);
            }
        });

    }
}
