package com.ljr.shoppingmall.home.adapter;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.home.activity.GoodsListActivity;
import com.ljr.shoppingmall.home.bean.TypeListBean;

import org.w3c.dom.Text;

import java.math.MathContext;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LinJiaRong on 2017/6/15.
 * TODO：
 */

public class GoodsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  Context mContext;
    private  List<TypeListBean.ResultBean.PageDataBean> page_data;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public GoodsListAdapter(GoodsListActivity context, List<TypeListBean.ResultBean.PageDataBean> pageDataBeen) {
        this.mContext = context;
        this.page_data = pageDataBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_goods_list_adapter, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(page_data.get(position));
    }

    @Override
    public int getItemCount() {
        return page_data.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIv_hot;
        private TextView mTv_name;
        private TextView mTv_price;
        private TypeListBean.ResultBean.PageDataBean mData;

        public ViewHolder(View itemView) {
            super(itemView);
            mIv_hot = (ImageView) itemView.findViewById(R.id.iv_hot);
            mTv_name = (TextView) itemView.findViewById(R.id.tv_name);
            mTv_price = (TextView) itemView.findViewById(R.id.tv_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.setOnItemClickListener(mData);

                    }
                }
            });
        }

        public void setData(TypeListBean.ResultBean.PageDataBean data) {
            mData = data;
            Glide.with(mContext).load(Constants.BASE_URL_IMAGE + mData.getFigure()).into(mIv_hot);
            mTv_name.setText(mData.getName());
            mTv_price.setText("￥"+mData.getCover_price());
        }

    }

    public interface OnItemClickListener{
        void setOnItemClickListener(TypeListBean.ResultBean.PageDataBean dataBean);
    }
}
