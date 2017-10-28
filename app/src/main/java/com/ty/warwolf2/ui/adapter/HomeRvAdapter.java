package com.ty.warwolf2.ui.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ty.warwolf2.R;
import com.ty.warwolf2.model.bean.base.Today;
import com.ty.warwolf2.util.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ 文件名:   HomeRvAdapter
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午4:33
 * @ 描述:    首页适配器
 */

public class HomeRvAdapter extends RecyclerView.Adapter<HomeRvAdapter.HomeViewHolder> {

    private List<Today> mList = new ArrayList<>();

    public List<Today> getList() {
        return mList;
    }

    public void setList(List<Today> list) {
        if (list != null) {
            mList.clear();
            addList(list);
        }
    }

    private void addList(List<Today> list) {
        if (list != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_today, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.bindData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class HomeViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView mTvTitle;
        private final ImageView mIvToady;
        private final AppCompatTextView mTvDes;
        private final AppCompatTextView mTvDay;
        private View mView;

        public HomeViewHolder(View view) {
            super(view);
            mView = view;
            mTvTitle = ((AppCompatTextView) mView.findViewById(R.id.tv_title));
            mIvToady = ((ImageView) mView.findViewById(R.id.iv_today));
            mTvDes = ((AppCompatTextView) mView.findViewById(R.id.tv_des));
            mTvDay = ((AppCompatTextView) mView.findViewById(R.id.tv_day));
        }

        public void bindData(Today today) {
            GlideUtil.into(mIvToady, today.getPic());
            mTvTitle.setText(today.getTitle());
            mTvDes.setText(today.getDes());
            mTvDay.setText("农历:" + today.getLunar());
        }

    }
}
