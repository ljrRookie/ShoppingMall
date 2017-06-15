package com.ljr.shoppingmall.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.TextView;

import com.ljr.shoppingmall.R;

/**
 * Created by LinJiaRong on 2017/6/4.
 * TODO：自定义删除添加按钮
 */

public class AddSubView extends LinearLayout implements View.OnClickListener {

    private final Context mContext;
    private ImageView mIv_sub;
    private TextView mTv_value;
    private ImageView mIv_add;
    private int mValue = 1;
    private OnNumberChangeListener mOnNumberChangeListener;
    private int mMinValue = 1;
    private int mMaxValue = 10;

    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        //把布局文件实例化,并且加载到AddSubView类中
        View.inflate(mContext, R.layout.add_sub, this);
        mIv_sub = (ImageView) findViewById(R.id.iv_sub);
        mTv_value = (TextView) findViewById(R.id.tv_value);
        mIv_add = (ImageView) findViewById(R.id.iv_add);

        mIv_add.setOnClickListener(this);
        mIv_sub.setOnClickListener(this);

        int value = getValue();
        setValue(value);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_sub:
                subNumber(v);
                break;
            case R.id.iv_add:
                addNumber(v);
                break;
        }
    }

    private void addNumber(View v) {
        if (mValue < mMaxValue) {
            mValue++;
        }
        setValue(mValue);
        if(mOnNumberChangeListener != null){
            mOnNumberChangeListener.addNumber(v,mValue);
        }
    }

    private void subNumber(View v) {
        if (mValue > mMinValue) {
            mValue--;
        }
        setValue(mValue);
        if(mOnNumberChangeListener != null){
            mOnNumberChangeListener.subNumner(v,mValue);
        }
    }

    public void setValue(int value) {
        this.mValue = value;
        mTv_value.setText(value + "");
    }


    public int getValue() {
        String valueStr = mTv_value.getText().toString().trim();
        if (!TextUtils.isEmpty(valueStr)) {
            mValue = Integer.parseInt(valueStr);
        }
        return mValue;
    }

    public int getMinValue() {
        return mMinValue;
    }

    public void setMinValue(int minValue) {
        this.mMinValue = minValue;
    }

    public int getMaxValue() {
        return mMaxValue;
    }

    public void setMaxValue(int maxValue) {
        this.mMaxValue = maxValue;
    }

    /**
     * 当数量发生变化的时候调用
     */
    public interface OnNumberChangeListener {
        /**
         * 当数据发生变化是回调
         * @param value
         */
        void addNumber(View view, int value);

        void subNumner(View view, int value);
    }

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.mOnNumberChangeListener = onNumberChangeListener;
    }
}
