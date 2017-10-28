package com.ty.warwolf2.ui.activity;

import android.os.Bundle;

import com.ty.warwolf2.R;
import com.ty.warwolf2.base.BaseActivity;
import com.ty.warwolf2.base.LoadingPager;
import com.ty.warwolf2.ui.adapter.MainPagerAdapter;
import com.ty.warwolf2.ui.fragment.CategoryFragment;
import com.ty.warwolf2.ui.fragment.FindFragment;
import com.ty.warwolf2.ui.fragment.HomeFragment;
import com.ty.warwolf2.ui.fragment.MineFragment;
import com.ty.warwolf2.view.BottomNavigationViewEx;
import com.ty.warwolf2.view.TViewPager;

/**
 * @ 文件名:   MainActivity
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 上午10:33
 * @ 描述:    主activity
 */
public class MainActivity extends BaseActivity {

    private BottomNavigationViewEx mNavigationView;
    private TViewPager mViewPager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //对底部导航栏的一些处理显示字体，启用放大动画，关闭item和navigator的位移动画
        mNavigationView = ((BottomNavigationViewEx) mView.findViewById(R.id.navigation));
        mViewPager = ((TViewPager) mView.findViewById(R.id.view_pager));
        mNavigationView.setTextVisibility(true);
        mNavigationView.enableAnimation(true);
        mNavigationView.enableShiftingMode(false);
        mNavigationView.enableItemShiftingMode(false);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        HomeFragment homeFragment = new HomeFragment();
        CategoryFragment categoryFragment = new CategoryFragment();
        FindFragment findFragment = new FindFragment();
        MineFragment mineFragment = new MineFragment();

        pagerAdapter.addFragment(homeFragment);
        pagerAdapter.addFragment(categoryFragment);
        pagerAdapter.addFragment(findFragment);
        pagerAdapter.addFragment(mineFragment);
        mViewPager.setAdapter(pagerAdapter);
        mPager.onDataLoading(LoadingPager.LoadedResult.SUCCESS);

    }

    @Override
    protected void reloadData() {
        mPager.onDataLoading(LoadingPager.LoadedResult.SUCCESS);
    }

    @Override
    protected void initListener() {
        mNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0, false);
                    break;
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(1, false);
                    break;
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(2, false);
                    break;
                case R.id.navigation_mine:
                    mViewPager.setCurrentItem(3, false);
                    break;
            }

            return false;
        });
    }
}
