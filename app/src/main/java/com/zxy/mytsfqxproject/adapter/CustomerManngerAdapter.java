package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.ChooseCustomer;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

public class CustomerManngerAdapter extends CommonRecycleAdapter<ChooseCustomer.ResultBean.DataBean> {
    private Context mContext;

    public CustomerManngerAdapter(Context context) {
        super(context, R.layout.item_choose_customer);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<ChooseCustomer.ResultBean.DataBean> data) {
        super.setDataList(data);
    }

    @Override
    public void addDataList(List<ChooseCustomer.ResultBean.DataBean> dataList) {
        super.addDataList(dataList);
    }

    @Override
    public void bindData(CommonViewHolder holder, ChooseCustomer.ResultBean.DataBean item) {
        holder.setText(R.id.tv_name, item.getUsername());
        holder.setText(R.id.tv_phone, item.getPhone());
        if (item.getSex().equals("男")) {
            ((ImageView) holder.getView(R.id.iv_nan)).setImageResource(R.mipmap.img_nan);
        } else {
            ((ImageView) holder.getView(R.id.iv_nan)).setImageResource(R.mipmap.img_nv);
        }

    }
}
