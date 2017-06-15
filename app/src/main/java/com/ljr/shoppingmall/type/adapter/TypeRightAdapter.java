package com.ljr.shoppingmall.type.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ljr.shoppingmall.GoodsInfoActivity;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.Utils.DensityUtil;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.home.bean.GoodsBean;
import com.ljr.shoppingmall.type.bean.TypeBean;

import java.util.List;

/**
 * Created by LinJiaRong on 2017/6/5.
 * TODO：
 */

public class TypeRightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<TypeBean.ResultBean.ChildBean> mChildBeen;
    private List<TypeBean.ResultBean.HotProductListBean> mHotProductListBeen;
    /**
     * 热卖
     */
    public static final int HOT = 0;
    /**
     * 普通的
     */
    public static final int ORDINARY = 1;


    /**
     * 当前的类型
     */
    public int currentType;

    public TypeRightAdapter(Context context, List<TypeBean.ResultBean> resultBeen) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        if (resultBeen != null && resultBeen.size() > 0) {
            mChildBeen = resultBeen.get(0).getChild();
            mHotProductListBeen = resultBeen.get(0).getHot_product_list();
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HOT) {
            return new HotViewHolder(mLayoutInflater.inflate(R.layout.item_hot_right, null), mContext);
        } else {
            return new OrdinaryViewHolder(mLayoutInflater.inflate(R.layout.item_ordinary_right,null), mContext);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
if(getItemViewType(position)==HOT){
    HotViewHolder hotViewHolder = (HotViewHolder) holder;
    hotViewHolder.setdata(mHotProductListBeen);
}else{
    OrdinaryViewHolder ordinaryViewHolder = (OrdinaryViewHolder) holder;
    ordinaryViewHolder.setdata(mChildBeen.get(position - 1),position);
}
    }

    @Override
    public int getItemCount() {
        return mChildBeen.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == HOT) {
            currentType = HOT;
        } else {
            currentType = ORDINARY;
        }
        return currentType;
    }

    private class HotViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private LinearLayout mLinear;
        public HotViewHolder(View inflate, Context context) {
            super(inflate);
            this.mContext = context;
            mLinear = (LinearLayout) itemView.findViewById(R.id.linear);
        }

        public void setdata(final List<TypeBean.ResultBean.HotProductListBean> hotProductListBeen) {
            for (int i = 0; i < hotProductListBeen.size(); i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                final LinearLayout layout = new LinearLayout(mContext);
                params.setMargins(DensityUtil.dip2px(mContext,5),0,DensityUtil.dip2px(mContext,5),DensityUtil.dip2px(mContext,20));
                layout.setOrientation(LinearLayout.VERTICAL);
                //添加到孩子里
                mLinear.addView(layout,params);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtil.dip2px(mContext, 80), DensityUtil.dip2px(mContext, 80));
                ImageView imageView = new ImageView(mContext);
                Glide.with(mContext).load(Constants.BASE_URL_IMAGE+hotProductListBeen.get(i).getFigure()).into(imageView);
                layoutParams.setMargins(0,0,0,DensityUtil.dip2px(mContext,10));
                layout.addView(imageView,layoutParams);


                LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                TextView textView = new TextView(mContext);
                textView.setText("￥" + hotProductListBeen.get(i).getCover_price());
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.parseColor("#ed3f3f"));
                layout.addView(textView,textViewLp);

                layout.setTag(i);

                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = (int) layout.getTag();
                        String cover_price = hotProductListBeen.get(i).getCover_price();
                        String name = hotProductListBeen.get(i).getName();
                        String figure = hotProductListBeen.get(i).getFigure();
                        String product_id = hotProductListBeen.get(i).getProduct_id();
                        GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);

                        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                        intent.putExtra("goodsBean",goodsBean);
                        mContext.startActivity(intent);

                    }
                });
            }
        }
    }

    private class OrdinaryViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ImageView iv_ordinary_right;
        private TextView tv_ordinary_right;
        private LinearLayout ll_root;
        public OrdinaryViewHolder(View inflate, Context context) {
            super(inflate);
            this.mContext = context;
            iv_ordinary_right = (ImageView) itemView.findViewById(R.id.iv_ordinary_right);
            tv_ordinary_right = (TextView) itemView.findViewById(R.id.tv_ordinary_right);
            ll_root = (LinearLayout) itemView.findViewById(R.id.ll_root);
        }

        public void setdata(TypeBean.ResultBean.ChildBean childBean, final int position) {
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+childBean.getPic()).into(iv_ordinary_right);
            tv_ordinary_right.setText(childBean.getName());
            ll_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "position==="+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
