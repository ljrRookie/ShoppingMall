package com.ljr.shoppingmall.type;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.base.BaseFragment;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.type.adapter.TypeLeftAdapter;
import com.ljr.shoppingmall.type.adapter.TypeRightAdapter;
import com.ljr.shoppingmall.type.bean.TypeBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by LinJiaRong on 2017/6/5.
 * TODO：
 */

public class ListFragment extends BaseFragment {
    private static final String TAG = ListFragment.class.getSimpleName();
    private ListView lv_left;
    private RecyclerView rv_right;
    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};
    private boolean isFirst = true;
    private TypeLeftAdapter mTypeLeftAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_list, null);
        lv_left = (ListView) view.findViewById(R.id.lv_left);
        rv_right = (RecyclerView) view.findViewById(R.id.rv_right);
        return view;
    }

    @Override
    public void initData() {
        getDataFromNet(urls[0]);
    }

    private void getDataFromNet(String url) {
        Log.e(TAG, "getDataFromNet: "+url);
        OkHttpUtils.get().url(url).id(100).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        switch (id) {
                            case 100:
                                if(response!=null){
                                    Log.e(TAG, "onResponse: "+response);
                                    List<TypeBean.ResultBean> resultBeen = processData(response);
                                    if(isFirst){
                                        mTypeLeftAdapter = new TypeLeftAdapter(mContext);
                                        lv_left.setAdapter(mTypeLeftAdapter);
                                    }
                                    initListener(mTypeLeftAdapter);
                                    TypeRightAdapter typeRightAdapter = new TypeRightAdapter(mContext, resultBeen);
                                    rv_right.setAdapter(typeRightAdapter);
                                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
                                    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                        @Override
                                        public int getSpanSize(int position) {
                                            if(position == 0){
                                                return 3;
                                            }else {
                                                return 1;
                                            }
                                        }
                                    });
                                    rv_right.setLayoutManager(gridLayoutManager);
                                }
                                break;
                            case 101:
                                Toast.makeText(mContext,"https",Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                });
    }

    private void initListener(final TypeLeftAdapter typeLeftAdapter) {
    //点击监听
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            typeLeftAdapter.changeSelected(position);
                if(position != 0){
                    isFirst = false;
                }
                getDataFromNet(urls[position]);
                mTypeLeftAdapter.notifyDataSetChanged();
            }
        });
        //选中监听
        lv_left.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
typeLeftAdapter.changeSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private List<TypeBean.ResultBean> processData(String response) {
        Gson gson = new Gson();
        TypeBean typeBean = gson.fromJson(response, TypeBean.class);
        List<TypeBean.ResultBean> result = typeBean.getResult();
        return result;
    }
}
