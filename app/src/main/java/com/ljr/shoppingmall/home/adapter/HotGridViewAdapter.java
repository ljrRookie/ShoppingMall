package com.ljr.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.home.bean.ResultBeanData;

import java.util.List;

/**
 * Created by LinJiaRong on 2017/6/3.
 * TODO：
 */

class HotGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.HotInfoBean> datas;

    public HotGridViewAdapter(Context context, List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
        this.mContext = context;
        this.datas = hot_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_hot,null);
            mViewHolder = new ViewHolder();
            mViewHolder. mIvHot = (ImageView) convertView.findViewById(R.id.iv_hot);
            mViewHolder. mTvName = (TextView) convertView.findViewById(R.id.tv_name);
            mViewHolder. mTvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder= (ViewHolder) convertView.getTag();
        }
        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+hotInfoBean.getFigure()).into(mViewHolder.mIvHot);
        mViewHolder.mTvName.setText(hotInfoBean.getName());
        mViewHolder.mTvPrice.setText("￥"+hotInfoBean.getCover_price());
        return convertView;
    }

    static class ViewHolder {
         ImageView mIvHot;
         TextView mTvName;
         TextView mTvPrice;

    }
}
