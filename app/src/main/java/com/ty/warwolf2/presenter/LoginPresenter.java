package com.ty.warwolf2.presenter;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.ty.warwolf2.base.BaseActivity;
import com.ty.warwolf2.base.BasePresenter;
import com.ty.warwolf2.base.LoadingPager;
import com.ty.warwolf2.config.ConstZh;
import com.ty.warwolf2.util.KeyboardUtil;

/**
 * @ 文件名:   LoginPresenter
 * @ 创建者:   ty
 * @ 时间:    2017/8/11 上午9:14
 * @ 描述:    登录VM
 */

public class LoginPresenter extends BasePresenter {

    private static final String TAG = "LoginPresenter";
    private final ProgressDialog mDialog;
    //private Subscription mSubscribe;

    public LoginPresenter(LoadingPager pager, BaseActivity activity) {
        super(pager, activity);
        mDialog = new ProgressDialog(mActivity);
        mDialog.setCancelable(false);
        mDialog.setMessage(ConstZh.LOGIN_LOADING);
    }

    public void login(String phone, String password) {
        KeyboardUtil.hide(mActivity);
        Toast.makeText(mActivity, "phone:" + phone + " ,password:" + password, Toast.LENGTH_SHORT).show();
        /*mSubscribe = RetrofitFactory.sApiService.login(phone,password)
                .map(new TFunc1<>(mPager))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new TSubscriber<User>(mPager) {
                    @Override
                    public void onDataSuccess(User user) {
                        mDialog.dismiss();

                    }

                    @Override
                    public void onDataError(String message) {
                        super.onDataError(message);
                        mDialog.dismiss();
                    }
                });
        addSubscribe(mSubscribe);*/
    }

}
