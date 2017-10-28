package com.ty.warwolf2.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.ty.warwolf2.R;
import com.ty.warwolf2.listener.DataLoadListener;
import com.ty.warwolf2.util.UIUtil;

/**
 * @ 文件名:   LoadingPager
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 上午10:34
 * @ 描述:
 */

public abstract class LoadingPager extends FrameLayout implements DataLoadListener, View.OnClickListener {
    /**
     * 无状态
     */
    private static final int STATE_NONE = -1;
    /**
     * 加载中的状态
     */
    public static final int STATE_LOADING = 0;
    /**
     * 空的状态
     */
    public static final int STATE_EMPTY = 1;
    /**
     * 错误的状态
     */
    public static final int STATE_ERROR = 2;
    /**
     * 成功的状态
     */
    public static final int STATE_SUCCESS = 3;
    /**
     * 当前view的状态
     */
    private int mCurrentState = STATE_NONE;
    /**
     * 正在加载中的view
     */
    private View mLoadingView;
    /**
     * 没有数据的view
     */
    private View mEmptyView;
    /**
     * 加载失败的view
     */
    private View mErrorView;
    /**
     * 成功的view
     */
    private View mSuccessView;

    private LayoutInflater inflate;

    public LoadingPager(Context context) {
        this(context, null);
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initView(context);
    }


    /**
     * 初始化
     */
    private void initView(Context context) {
        /**
         * 1. 加载数据
         */
        if (mLoadingView == null) {
            mLoadingView = LayoutInflater.from(context).inflate(R.layout.page_loading, null, false);
            addView(mLoadingView);
        }

        /**
         * 2. 数据为空
         */
        if (mEmptyView == null) {
            mEmptyView = LayoutInflater.from(context).inflate(R.layout.pager_empty, null, false);
            addView(mEmptyView);
            mEmptyView.setOnClickListener(this);
        }

        /**
         * 3. 加载失败
         */
        if (mErrorView == null) {
            mErrorView = LayoutInflater.from(context).inflate(R.layout.pager_error, null, false);
            addView(mErrorView);
            mErrorView.setOnClickListener(this);
        }
        /**
         * 4. 成功
         */
        if (mSuccessView == null) {
            mSuccessView = onCreateSuccessView();
            FrameLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            addView(mSuccessView, params);
        }

        safeUpdateUIStyle();
    }


    /**
     * 根据状态显示view
     */
    private void safeUpdateUIStyle() {
        UIUtil.runInMainThread(new Runnable() {
            @Override
            public void run() {
                updateUIStyle();
            }
        });
    }

    private void updateUIStyle() {

        /**
         * 1.loading
         */
        if (mLoadingView != null) {
            if ((mCurrentState == STATE_LOADING) || (mCurrentState == STATE_NONE)) {
                mLoadingView.setVisibility(VISIBLE);
            } else {
                mLoadingView.setVisibility(GONE);
            }
            if (mCurrentState == STATE_LOADING) {
                bringChildToFront(mLoadingView);
                return;
            }
        }

        /**
         * 2. empty
         */
        if (mEmptyView != null) {
            if (mCurrentState == STATE_EMPTY) {
                mEmptyView.setVisibility(VISIBLE);
            } else {
                mEmptyView.setVisibility(GONE);
            }
            if (mCurrentState == STATE_EMPTY) {
                bringChildToFront(mEmptyView);
            }
        }


        /**
         * 3. error
         */
        if (mErrorView != null) {
            if (mCurrentState == STATE_ERROR) {
                mErrorView.setVisibility(VISIBLE);
            } else {
                mErrorView.setVisibility(GONE);
            }
            if (mCurrentState == STATE_ERROR) {
                bringChildToFront(mErrorView);
            }
        }

        /**
         * 4. success
         */
        if (mSuccessView != null) {
            if (mCurrentState == STATE_SUCCESS) {
                mSuccessView.setVisibility(VISIBLE);
            } else {
                mSuccessView.setVisibility(GONE);
            }
        }
    }

    /**
     * 加载数据的方法
     */
    public void loadData() {
        if (mCurrentState == STATE_EMPTY || mCurrentState == STATE_ERROR || mCurrentState == STATE_NONE) {
            mCurrentState = STATE_LOADING;
            onStartLoadData();
        }
        safeUpdateUIStyle();
    }

    @Override
    public void onDataLoading(LoadedResult result) {
        mCurrentState = result.getState();
        safeUpdateUIStyle();
    }

    protected abstract View onCreateSuccessView();

    protected abstract void onStartLoadData();

    public View getErrorView() {
        return mErrorView;
    }

    public View getLoadingView() {
        return mLoadingView;
    }

    public View getEmptyView() {
        return mEmptyView;
    }

    public int getCurrentState() {
        return mCurrentState;
    }

    protected void reloadData() {
    }

    public enum LoadedResult {
        LOADING(STATE_LOADING), EMPTY(STATE_EMPTY), ERROR(STATE_ERROR), SUCCESS(STATE_SUCCESS);

        int state;

        LoadedResult(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_reload) {
            mCurrentState = STATE_LOADING;
            safeUpdateUIStyle();
            reloadData();
        }
    }

}
