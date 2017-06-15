package com.ljr.shoppingmall.shoppingcart.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.MutableChar;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ljr.shoppingmall.Utils.CacheUtils;
import com.ljr.shoppingmall.base.MyApplication;
import com.ljr.shoppingmall.home.bean.GoodsBean;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LinJiaRong on 2017/6/4.
 * TODO：
 */

public class CartStorage {
    private static final String JSON_CART = "json_cart";
    private static final String TAG = CartStorage.class.getSimpleName();
    private Context mContext;
    //SparseArray的性能优于HashMap
    private SparseArray<GoodsBean> mSparseArray;
    private static CartStorage sCartStorage;


    public CartStorage(Context context) {
        this.mContext = context;
        //读取之前存储的数据
        mSparseArray = new SparseArray<>(100);
        listToSparseArray();
    }

    /**
     * 获得购物车单例
     *
     * @return
     */
    public static CartStorage getInstance() {
        if (sCartStorage == null) {
            sCartStorage = new CartStorage(MyApplication.getContext());
        }
        return sCartStorage;
    }

    /**
     * 从本地读取的数据加入到SparseArray
     */
    private void listToSparseArray() {
        List<GoodsBean> allData = getAllData();
        //把List数据转换成SparesArray
        if (allData != null && allData.size() > 0) {
            for (int i = 0; i < allData.size(); i++) {
                GoodsBean goodsBean = allData.get(i);
                mSparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);

            }
        }
    }

    /**
     * 获取本地的所有数据
     *
     * @return
     */
    public List<GoodsBean> getAllData() {
        List<GoodsBean> mAllData = new ArrayList<>();
        //从本地获取
        String json = CacheUtils.getString(mContext, JSON_CART);
        //使用Gson转换成列表
        Log.e(TAG, "getAllData: ============" + json);
        if (json != null) {
            //把String转换成list"
            mAllData = new Gson().fromJson(json, new TypeToken<List<GoodsBean>>() {
            }.getType());
        }
        return mAllData;
    }

    /**
     * 添加数据
     */
    public void addData(GoodsBean goodsBean) {
        //1.添加到内存中SparesAray
        //如果添加的某条数据已存在则number+1
        GoodsBean tempData = mSparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if (tempData != null) {
            //内存中存在这条数据
            tempData.setNumber(tempData.getNumber() + 1);
        } else {
            tempData = goodsBean;
            tempData.setNumber(1);
        }
        //同步到内存中
        mSparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), tempData);
        //同步到本地
        saveLocal();
    }


    /**
     * 删除数据
     *
     * @param goodsBean
     */
    public void deleteData(GoodsBean goodsBean) {
        //1.内存中删除
        mSparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));

        //2.同步到本地
        saveLocal();
    }

    /**
     * 更新数据
     *
     * @param goodsBean
     */
    public void updateData(GoodsBean goodsBean) {
        //1.内存中更新
        mSparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);

        //2.同步到本地
        saveLocal();
    }

    private void saveLocal() {
        //把SparseArray转换成list
        List<GoodsBean> goodsBeenList = sparseToList();
        //使用Gson把List转换成String
        String json = new Gson().toJson(goodsBeenList);
        //保存json
        CacheUtils.saveString(mContext, JSON_CART, json);
    }

    private List<GoodsBean> sparseToList() {
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        for (int i = 0; i < mSparseArray.size(); i++) {
            GoodsBean goodsBean = mSparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }
}
