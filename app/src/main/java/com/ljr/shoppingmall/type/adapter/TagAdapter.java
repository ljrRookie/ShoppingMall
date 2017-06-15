package com.ljr.shoppingmall.type.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.type.bean.TagBean;

import java.net.ConnectException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LinJiaRong on 2017/6/5.
 * TODO：
 */

public class TagAdapter extends BaseAdapter {
    private final List<TagBean.ResultBean> datas;
    private Context mContext;
    private int[] colors = {Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"),
            Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"), Color.parseColor("#f0a420"),
            Color.parseColor("#f0839a"), Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2")
    };
    public TagAdapter(Context context, List<TagBean.ResultBean> resultBeen) {
        this.mContext = context;
        this.datas = resultBeen;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView  = View.inflate(mContext, R.layout.item_tab, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final TagBean.ResultBean resultBean = datas.get(position);
        holder.mTvTag.setText(resultBean.getName());
        holder.mTvTag.setTextColor(colors[position % colors.length]);
        holder.mTvTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "position=="+resultBean.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_tag)
        TextView mTvTag;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
