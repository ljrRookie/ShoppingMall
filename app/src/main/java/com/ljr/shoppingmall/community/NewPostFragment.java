package com.ljr.shoppingmall.community;

import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.base.BaseFragment;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.community.adapter.NewPostAdapter;
import com.ljr.shoppingmall.community.bean.NewPostBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by LinJiaRong on 2017/6/5.
 * TODO：
 */

public class NewPostFragment extends BaseFragment{
    private final String TAG = NewPostFragment.class.getSimpleName();
    private ListView mLv_new_post;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_new_post, null);
        mLv_new_post = (ListView) view.findViewById(R.id.lv_new_post);
        return view;
    }

    @Override
    public void initData() {
getDataFromNet();
    }

    public void getDataFromNet() {
        OkHttpUtils.get().url(Constants.NEW_POST_URL).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
            switch(id){
                case 100 :
                    if(response != null){
                        List<NewPostBean.ResultBean> resultBeen = processData(response);
                        NewPostAdapter newPostAdapter = new NewPostAdapter(mContext, resultBeen);
                        mLv_new_post.setAdapter(newPostAdapter);
                    }
                       break;
                default:
                       break;
            }
            }
        });
    }

    private List<NewPostBean.ResultBean> processData(String json) {
        Gson gson = new Gson();
        NewPostBean newPostBean = gson.fromJson(json, NewPostBean.class);
        List<NewPostBean.ResultBean> result = newPostBean.getResult();
        return result;
    }
}
