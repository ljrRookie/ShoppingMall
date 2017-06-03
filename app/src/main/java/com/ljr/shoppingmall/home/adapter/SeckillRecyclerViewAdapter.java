package com.ljr.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.GenericRequest;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.home.bean.ResultBeanData;

import java.util.List;

import butterknife.OnItemClick;

/**
 * Created by LinJiaRong on 2017/6/2.
 * TODO：
 */

class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHodler> {
    private Context mContext;
    private List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> mListData;

    /**
     * 监听事件
     */
    public interface OnSeckillRecyclerView {
        /**
         * 当某条被点击的时候回调
         * @param position
         */
        public void onItemClick(int position);
    }

    private OnSeckillRecyclerView mOnSeckillRecyclerView;

    /**\
     * 设置Item的监听
     * @param onSeckillRecyclerView
     */
    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView) {
        this.mOnSeckillRecyclerView = onSeckillRecyclerView;
    }

    public SeckillRecyclerViewAdapter(Context context, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> seckill_info) {
        this.mContext = context;
        this.mListData = seckill_info;

    }

    class ViewHodler extends RecyclerView.ViewHolder {
        private ImageView mIvFigure;
        private TextView mTvCoverPrice;
        private TextView mTvOriginPrice;

        public ViewHodler(View itemView) {
            super(itemView);
            mIvFigure = (ImageView) itemView.findViewById(R.id.iv_figure);
            mTvCoverPrice = (TextView) itemView.findViewById(R.id.tv_cover_price);
            mTvOriginPrice = (TextView) itemView.findViewById(R.id.tv_origin_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(mContext, "秒杀=" + getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    if(mOnSeckillRecyclerView!=null){
                        mOnSeckillRecyclerView.onItemClick(getLayoutPosition());

                    }
                }
            });
        }
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_seckill, null);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = mListData.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + listBean.getFigure()).into(holder.mIvFigure);
        holder.mTvCoverPrice.setText(listBean.getCover_price());
        holder.mTvOriginPrice.setText(listBean.getOrigin_price());
    }


    @Override
    public int getItemCount() {
        return mListData.size();
    }
}
