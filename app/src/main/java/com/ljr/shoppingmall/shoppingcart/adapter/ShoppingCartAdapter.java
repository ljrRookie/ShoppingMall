package com.ljr.shoppingmall.shoppingcart.adapter;

import android.app.job.JobInfo;
import android.content.Context;
import android.support.constraint.solver.Cache;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.Utils.CacheUtils;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.home.bean.GoodsBean;
import com.ljr.shoppingmall.shoppingcart.util.CartStorage;
import com.ljr.shoppingmall.view.AddSubView;

import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * Created by LinJiaRong on 2017/6/4.
 * TODO：购物车适配器
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private Context mContext;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private CheckBox checkboxAll;
    //完成状态下的删除CheckBox
    private CheckBox cbAll;
    private OnItemClickListener mOnItemClickListener;
    private CartStorage mCartStorage;


    public ShoppingCartAdapter(Context context, List<GoodsBean> goodsBeanList, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox cbAll) {
        mCartStorage = CartStorage.getInstance();
        this.mContext = context;
        this.datas = goodsBeanList;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.cbAll = cbAll;
        //首次加载数据
        showTotalPrice();
        checkboxAll.setChecked(true);
        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setSelected(true);
        }
        showTotalPrice();
        //设置点击事件
        setListener();
        //校验是否全选
        checkAll();
    }

    public void checkAll() {
        if (datas != null && datas.size() > 0) {
            int number = 0;
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (!goodsBean.isSelected()) {
                    // 没有全选
                    checkboxAll.setChecked(false);
                    cbAll.setChecked(false);
                } else {
                    //选中的
                    number++;
                }
            }
            if (number == datas.size()) {
                //全选
                checkboxAll.setChecked(true);
                cbAll.setChecked(true);
            }
        } else {
            checkboxAll.setChecked(false);
            cbAll.setChecked(false);
        }
    }

    public void checkAll_none(boolean isChecked) {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                goodsBean.setSelected(isChecked);
                notifyItemChanged(i);
            }
        }else{
            checkboxAll.setChecked(false);
            cbAll.setChecked(false);
        }
    }

    private void setListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                GoodsBean goodsBean = datas.get(position);
                goodsBean.setSelected(!goodsBean.isSelected());
                notifyItemChanged(position);
                //校验是否全选
                checkAll();
                //重新计算总价格
                showTotalPrice();
            }

        });
        //结算全选的点击事件
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.得到状态
                boolean isChecked = getCheckboxAll().isChecked();
                //2.根据状态设置全选和非全选
                checkAll_none(isChecked);
                //3.计算总价格
                showTotalPrice();
            }
        });
        //删除全选的点击事件
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = getCbAll().isChecked();
                checkAll_none(checked);
                showTotalPrice();
            }
        });
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText("总价：" + getTotalPrice());
    }

    /**
     * 计算总价格
     *
     * @return
     */
    public double getTotalPrice() {
        double mTotalPrice = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isSelected()) {
                    mTotalPrice = mTotalPrice + Double.valueOf(goodsBean.getNumber()) * Double.valueOf(goodsBean.getCover_price());
                }
            }
        }
        return mTotalPrice;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_shopping_cart, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final GoodsBean goodsBean = datas.get(position);
        holder.cb_gov.setChecked(goodsBean.isSelected());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(goodsBean.getName());
        holder.tv_price_gov.setText("￥" + goodsBean.getCover_price());
        holder.addSubView.setValue(goodsBean.getNumber());
        holder.addSubView.setMinValue(1);
        holder.addSubView.setMaxValue(8);
        //设置商品数量变化
        holder.addSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void addNumber(View view, int value) {
                //1.当前列表内存中
                goodsBean.setNumber(value);
                //2.本地更新
                mCartStorage.updateData(goodsBean);
                //3.刷新适配器
                notifyItemChanged(position);
                //4.重新计算总价
                showTotalPrice();
            }

            @Override
            public void subNumner(View view, int value) {
                //1.当前列表内存中
                goodsBean.setNumber(value);
                //2.本地更新
                mCartStorage.updateData(goodsBean);
                //3.刷新适配器
                notifyItemChanged(position);
                //4.重新计算总价
                showTotalPrice();
            }


        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private AddSubView addSubView;

        public ViewHolder(View itemView) {
            super(itemView);
            cb_gov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            iv_gov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            addSubView = (AddSubView) itemView.findViewById(R.id.ddSubView);
            //设置item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(v,getLayoutPosition());

                }
                }
            });
        }
    }

    /**
     * 点击Item的监听
     */
    public interface OnItemClickListener {
        /**
         * 当点击某条的时候调用
         */
        public void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public void deleteData(){
        if(datas != null && datas.size() > 0){
            for(Iterator iterator = datas.iterator();iterator.hasNext();){
                GoodsBean cart = (GoodsBean) iterator.next();
                if(cart.isSelected()){
                    int position = datas.indexOf(cart);
                    //本地删除
                    mCartStorage.deleteData(cart);
                    //内存删除
                    iterator.remove();
                    //刷新
                    notifyItemRemoved(position);

                }
            }
        }
    }

    public CheckBox getCheckboxAll() {
        return checkboxAll;
    }

    public CheckBox getCbAll() {
        return cbAll;
    }

    public void setCheckboxAll(CheckBox checkboxAll) {
        this.checkboxAll = checkboxAll;
    }

    public void setCbAll(CheckBox cbAll) {
        this.cbAll = cbAll;
    }
}

