package com.ljr.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
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

class RecommendGridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ResultBeanData.ResultBean.RecommendInfoBean> datas;


    public RecommendGridViewAdapter(Context context, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        this.mContext = context;
        this.datas = recommend_info;

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
        if(convertView==null){
            convertView = View.inflate(mContext, R.layout.item_recommend, null);
            mViewHolder = new ViewHolder();
            mViewHolder.iv_recommend = (ImageView) convertView.findViewById(R.id.iv_recommend);
            mViewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            mViewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+recommendInfoBean.getFigure()).into(mViewHolder.iv_recommend);
        mViewHolder.tv_name.setText(recommendInfoBean.getName());
        mViewHolder.tv_price.setText("￥"+recommendInfoBean.getCover_price());
        return convertView;
    }

    static class ViewHolder {
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}
