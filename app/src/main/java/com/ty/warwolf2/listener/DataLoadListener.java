package com.ty.warwolf2.listener;


import com.ty.warwolf2.base.LoadingPager;

/**
 * @ 文件名:   DataLoadListener
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:24
 * @ 描述:
 */

public interface DataLoadListener {

    void onDataLoading(LoadingPager.LoadedResult result);

}
