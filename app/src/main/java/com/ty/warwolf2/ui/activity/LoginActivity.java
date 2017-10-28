package com.ty.warwolf2.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ty.warwolf2.R;
import com.ty.warwolf2.base.BaseActivity;
import com.ty.warwolf2.base.LoadingPager;
import com.ty.warwolf2.config.ConstZh;
import com.ty.warwolf2.presenter.LoginPresenter;

/**
 * @ 文件名:   LoginActivity
 * @ 创建者:   ty
 * @ 时间:    2017/8/11 上午9:11
 * @ 描述:    登录页面
 */
public class LoginActivity extends BaseActivity {


    private LoginPresenter mViewModel;
    private Button mBtnLogin;
    private EditText mETPassword;
    private EditText mETPhone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mToolbar.setTitle(ConstZh.LOGIN);
        mBtnLogin = ((Button) mView.findViewById(R.id.btn_login));
        mETPhone = ((EditText) mView.findViewById(R.id.et_phone));
        mETPassword = (EditText) mView.findViewById(R.id.et_pass_word);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPager.onDataLoading(LoadingPager.LoadedResult.SUCCESS);

        mViewModel = new LoginPresenter(mPager, this);


    }

    @Override
    protected void reloadData() {
        mPager.onDataLoading(LoadingPager.LoadedResult.SUCCESS);
    }

    @Override
    protected void initListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mETPhone.getText().toString().trim();
                String password = mETPassword.getText().toString().trim();
                if (checkPassWord(phone, password)) {
                    mViewModel.login(phone, password);
                } else {
                    Toast.makeText(LoginActivity.this, "请输入用户名密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 检查密码是否合法
     *
     * @param phone
     * @param password
     * @return
     */
    public boolean checkPassWord(String phone, String password) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }
}
