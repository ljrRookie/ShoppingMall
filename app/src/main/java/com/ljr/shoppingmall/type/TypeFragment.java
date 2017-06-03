package com.ljr.shoppingmall.type;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.ljr.shoppingmall.base.BaseFragment;

/**
 * Created by LinJiaRong on 2017/5/10.
 * TODO：首页
 */

public class TypeFragment extends BaseFragment {
    private static final String TAG = TypeFragment.class.getSimpleName();
    private TextView textView;
    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        Log.e(TAG, "initData:主页数据被初始化了 ");
        textView.setText("分类");
    }
}
