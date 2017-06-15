package com.ljr.shoppingmall.type.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ljr.shoppingmall.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LinJiaRong on 2017/6/5.
 * TODO：
 */

public class TypeLeftAdapter extends BaseAdapter {

    private final Context mContext;
    private int mSelect = 0;//选中项
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品", "办公文具", "数码周边", "游戏专区"};

    public TypeLeftAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_type, null);
            holder = new ViewHolder();
            holder.mTvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        }else{
             holder = (ViewHolder) convertView.getTag();
        }
        holder.mTvTitle.setText(titles[position]);
        if(mSelect == position){
            //选中项背景
            convertView.setBackgroundResource(R.drawable.type_item_background_selector);
            holder.mTvTitle.setTextColor(Color.parseColor("#fd3f3f"));
        }else{
            convertView.setBackgroundResource(R.drawable.bg2);
            holder.mTvTitle.setTextColor(Color.parseColor("#323437"));
        }
        return convertView;
    }
    public void changeSelected(int position){
        if(position != mSelect){
            mSelect = position;
            notifyDataSetChanged();
        }

    }

    static class ViewHolder {
       private TextView mTvTitle;


    }
}
