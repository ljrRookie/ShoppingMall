package com.ljr.shoppingmall.type;

import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.base.BaseFragment;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.type.adapter.TagAdapter;
import com.ljr.shoppingmall.type.bean.TagBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by LinJiaRong on 2017/6/5.
 * TODO：
 */

public class TagFragment extends BaseFragment {
    private static final String TAG = TagFragment.class.getSimpleName();
    private GridView mGridView;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_tag, null);
        mGridView = (GridView) view.findViewById(R.id.gv_tag);
        return view;
    }

    @Override
    public void initData() {
    getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.TAG_URL).id(100).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError:============ "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                    switch(id){
                        case 100 :
                            if(response != null){
                                Log.e(TAG, "onResponse: "+response );
                                List<TagBean.ResultBean> resultBeen = processData(response);
                                TagAdapter tagAdapter = new TagAdapter(mContext, resultBeen);
                                mGridView.setAdapter(tagAdapter);
                            }else{
                                Toast.makeText(mContext, "没有数据！！", Toast.LENGTH_SHORT).show();
                            }
                               break;
                        case 101 :
                            break;
                    }
                    }
                });

    }

    private List<TagBean.ResultBean> processData(String response) {
        Gson gson = new Gson();
        TagBean tagBean = gson.fromJson(response, TagBean.class);
        List<TagBean.ResultBean> result = tagBean.getResult();
return result;
    }
}
