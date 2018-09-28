package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.MenuBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

public class ChooseCustomerAdapter extends CommonRecycleAdapter<MenuBean.ResultBean> {
    private Context mContext;

    public ChooseCustomerAdapter(Context context) {
        super(context, R.layout.item_choose_customer);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<MenuBean.ResultBean> data) {
        super.setDataList(data);
    }

    @Override
    public void addDataList(List<MenuBean.ResultBean> dataList) {
        super.addDataList(dataList);
    }

    @Override
    public void bindData(CommonViewHolder holder, MenuBean.ResultBean item) {
    }
}
