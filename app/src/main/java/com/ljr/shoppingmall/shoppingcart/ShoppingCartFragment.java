package com.ljr.shoppingmall.shoppingcart;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.base.BaseFragment;
import com.ljr.shoppingmall.home.bean.GoodsBean;
import com.ljr.shoppingmall.shoppingcart.adapter.ShoppingCartAdapter;
import com.ljr.shoppingmall.shoppingcart.util.CartStorage;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LinJiaRong on 2017/5/10.
 * TODO：首页
 */

public class ShoppingCartFragment extends BaseFragment {
    private static final String TAG = ShoppingCartFragment.class.getSimpleName();
    /**
     * 编辑状态
     */
    private static final int ACTION_EDIT = 1;
    /**
     * 完成状态
     */
    private static final int ACTION_COMPLETE = 2;
    @Bind(R.id.tv_shopcart_edit)
    TextView mTvShopcartEdit;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @Bind(R.id.checkbox_all)
    CheckBox mCheckboxAll;
    @Bind(R.id.tv_shopcart_total)
    TextView mTvShopcartTotal;
    @Bind(R.id.btn_check_out)
    Button mBtnCheckOut;
    @Bind(R.id.ll_check_all)
    LinearLayout mLlCheckAll;
    @Bind(R.id.cb_all)
    CheckBox mCbAll;
    @Bind(R.id.btn_delete)
    Button mBtnDelete;
    @Bind(R.id.btn_collection)
    Button mBtnCollection;
    @Bind(R.id.ll_delete)
    LinearLayout mLlDelete;
    @Bind(R.id.iv_empty)
    LinearLayout mIvEmpty;
    @Bind(R.id.tv_empty_cart_tobuy)
    TextView mTvEmptyCartTobuy;
    @Bind(R.id.ll_empty_shopcart)
    LinearLayout mLlEmptyShopcart;
    private ShoppingCartAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);

        return view;
    }

    @Override
    public void initData() {

        mTvEmptyCartTobuy.setClickable(true);
        Log.e(TAG, "initData:主页数据被初始化了 ");
        initListener();
        mTvShopcartEdit.setTag(ACTION_EDIT);
        mTvShopcartEdit.setText("编辑");
        mLlCheckAll.setVisibility(View.VISIBLE);
        showData();
    }

    private void initListener() {
        //设置默认的编辑状态
        mTvShopcartEdit.setTag(ACTION_EDIT);
        mTvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int) v.getTag();
                if (action == ACTION_EDIT) {
                    //完成状态
                    showDelete();
                } else {
                    //编辑状态
                    hideDelete();
                }
            }
        });
    }

    private void hideDelete() {
        //1.设置状态和文本 -----编辑
        mTvShopcartEdit.setTag(ACTION_EDIT);
        mTvShopcartEdit.setText("编辑");
        if (mAdapter!=null) {
            mAdapter.checkAll_none(true);
        }
        //3.隐藏删除视图
        mLlDelete.setVisibility(View.GONE);
        //4.显示结算视图
        mLlCheckAll.setVisibility(View.VISIBLE);
    }

    private void showDelete() {
        //1.设置状态-文本 -----完成
        mTvShopcartEdit.setTag(ACTION_COMPLETE);
        mTvShopcartEdit.setText("完成");
        //2.变成非勾选
        if (mAdapter!=null) {
            mAdapter.checkAll_none(false);
            mCbAll.setChecked(false);
            mCheckboxAll.setChecked(false);
        }
        //3.显示删除视图
        mLlDelete.setVisibility(View.VISIBLE);
        //4.隐藏结算视图
        mLlCheckAll.setVisibility(View.GONE);
        mAdapter.showTotalPrice();
    }
    private void checkData() {
        if (mAdapter != null && mAdapter.getItemCount() > 0) {
            mTvShopcartEdit.setVisibility(View.VISIBLE);
            mLlEmptyShopcart.setVisibility(View.GONE);

        } else {
            mLlEmptyShopcart.setVisibility(View.VISIBLE);
            mTvShopcartEdit.setVisibility(View.GONE);

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    private void showData() {
        List<GoodsBean> goodsBeanList = CartStorage.getInstance().getAllData();
        if (goodsBeanList != null && goodsBeanList.size() > 0) {
            mTvShopcartEdit.setVisibility(View.VISIBLE);
            /*mLlCheckAll.setVisibility(View.VISIBLE);*/
            mLlEmptyShopcart.setVisibility(View.GONE);
            mAdapter = new ShoppingCartAdapter(mContext, goodsBeanList, mTvShopcartTotal, mCheckboxAll, mCbAll);
            mRecyclerview.setAdapter(mAdapter);
            mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

        }else{
            //没有数据显示空购物车布局
            emptyShoppingCart();
        }
    }

    private void emptyShoppingCart() {
        mLlEmptyShopcart.setVisibility(View.VISIBLE);
        mTvShopcartEdit.setVisibility(View.GONE);
        mLlDelete.setVisibility(View.GONE);
    }

    @OnClick({R.id.btn_check_out, R.id.btn_delete, R.id.btn_collection})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check_out:
                Toast.makeText(mContext, "结算！！！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                mAdapter.deleteData();
                mAdapter.showTotalPrice();
                checkData();
                mAdapter.checkAll();

                break;
            case R.id.btn_collection:
                Toast.makeText(mContext, "收藏！！！", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
