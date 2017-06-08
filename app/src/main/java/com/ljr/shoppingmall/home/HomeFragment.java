package com.ljr.shoppingmall.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.base.BaseFragment;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.home.adapter.HomeFragmentAdapter;
import com.ljr.shoppingmall.home.bean.ResultBeanData;
import com.ljr.shoppingmall.user.activity.MessageCenterActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by LinJiaRong on 2017/5/10.
 * TODO：首页
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    @Bind(R.id.search_home)
    TextView mSearchHome;
    @Bind(R.id.tv_message_home)
    TextView mTvMessageHome;
    @Bind(R.id.home_recyclerview)
    RecyclerView mHomeRecyclerview;
    @Bind(R.id.ib_top)
    ImageButton mIbTop;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        return view;
    }

    @Override
    public void initData() {
        Log.e(TAG, "initData:主页数据被初始化了 ");
        getDataFromNet();
    }

    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "onError: =="+e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e(TAG, "onResponse: =="+response);
                processData(response);
            }
        });
    }

    private void processData(String json) {
        ResultBeanData data = JSON.parseObject(json, ResultBeanData.class);
        ResultBeanData.ResultBean resultBean = data.getResult();
        if(resultBean != null){
            HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(mContext, resultBean);
            mHomeRecyclerview.setAdapter(homeFragmentAdapter);
            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position <= 3){
                        mIbTop.setVisibility(View.GONE);
                    }else{
                        mIbTop.setVisibility(View.VISIBLE);
                    }
                    //只能返回1
                    return 1;
                }
            });
            mHomeRecyclerview.setLayoutManager(manager);
        }else{
            Toast.makeText(mContext, "获取数据失败！！！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }



    @OnClick({R.id.search_home, R.id.ib_top,R.id.tv_message_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_home:
                Toast.makeText(mContext, "搜索吧！！！！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home:
                Intent intent = new Intent(mContext, MessageCenterActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.ib_top:
                mHomeRecyclerview.scrollToPosition(0);
                break;
        }
    }
}
