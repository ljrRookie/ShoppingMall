package com.ljr.shoppingmall.community;

import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.base.BaseFragment;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.community.adapter.HotPostListAdapter;
import com.ljr.shoppingmall.community.bean.HotPostBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by LinJiaRong on 2017/6/5.
 * TODO：
 */

public class HotPostFragment extends BaseFragment{
    private final String TAG = HotPostFragment.class.getSimpleName();
    private ListView lv_hot_post;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_hot_post, null);
        lv_hot_post = (ListView) view.findViewById(R.id.lv_hot_post);
        return view;
    }

    @Override
    public void initData() {
getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.HOT_POST_URL).id(100).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        switch(id){
                            case 100 :
                                if(response != null){
                                    List<HotPostBean.ResultBean> resultBeen = processData(response);
                                    HotPostListAdapter hotPostListAdapter = new HotPostListAdapter(mContext, resultBeen);
                                    lv_hot_post.setAdapter(hotPostListAdapter);
                                }
                                   break;
                            case 101 :
                                break;
                            default:
                                   break;
                        }
                    }
                });
    }

    private List<HotPostBean.ResultBean> processData(String json) {
        Gson gson = new Gson();
        HotPostBean hotPostBean = gson.fromJson(json, HotPostBean.class);
        List<HotPostBean.ResultBean> result = hotPostBean.getResult();
        return result;

    }
}
