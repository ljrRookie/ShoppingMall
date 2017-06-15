package com.ljr.shoppingmall.community.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.Utils.BitmapUtils;
import com.ljr.shoppingmall.Utils.DensityUtil;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.community.bean.HotPostBean;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LinJiaRong on 2017/6/5.
 * TODO：
 */

public class HotPostListAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<HotPostBean.ResultBean> datas;

    public HotPostListAdapter(Context context, List<HotPostBean.ResultBean> resultBeen) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hotpost_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HotPostBean.ResultBean resultBean = datas.get(position);
        holder.mTvHotUsername.setText(resultBean.getUsername());
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        holder.mTvHotAddtime.setText(format.format(Integer.parseInt(resultBean.getAdd_time())));
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + resultBean.getFigure()).into(holder.mIvHotFigure);
        holder.mTvHotSaying.setText(resultBean.getSaying());
        holder.mTvHotLikes.setText(resultBean.getLikes());
        holder.mTvHotComments.setText(resultBean.getComments());

        Picasso.with(mContext).load(resultBean.getAvatar()).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                //先对图片进行压缩
                Bitmap zoom = BitmapUtils.zoom(source, 70, 70);
                //圆形处理
                Bitmap ciceBitMap = BitmapUtils.circleBitmap(zoom);
                source.recycle();
                return ciceBitMap;
            }

            @Override
            public String key() {
                return "";
            }
        }).into(holder.mIbNewPostAvatar);

        String is_top = resultBean.getIs_top();
        if ("1".equals(is_top)) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(mContext);
            textView.setText("置顶");
            textViewLp.setMargins(DensityUtil.dip2px(mContext, 10), 0, DensityUtil.dip2px(mContext, 5), 0);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(R.drawable.is_top_shape);
            textView.setPadding(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            holder.mLlHotPost.removeAllViews();
            holder.mLlHotPost.addView(textView, textViewLp);
        }

        String is_hot = resultBean.getIs_hot();
        if ("1".equals(is_hot)) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(mContext);
            textView.setText("热门");
            textViewLp.setMargins(0, 0, DensityUtil.dip2px(mContext, 5), 0);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setPadding(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            textView.setBackgroundResource(R.drawable.is_hot_shape);
            holder.mLlHotPost.addView(textView, textViewLp);
        }

        String is_essence = resultBean.getIs_essence();
        if("1".equals(is_essence)){
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textViewLp.setMargins(0, 0, DensityUtil.dip2px(mContext, 5), 0);
            TextView textView = new TextView(mContext);
            textView.setText("精华");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setPadding(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            textView.setBackgroundResource(R.drawable.is_essence_shape);
            holder.mLlHotPost.addView(textView, textViewLp);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_hot_username)
        TextView mTvHotUsername;
        @Bind(R.id.tv_hot_addtime)
        TextView mTvHotAddtime;
        @Bind(R.id.iv_hot_figure)
        ImageView mIvHotFigure;
        @Bind(R.id.ll_hot_post)
        LinearLayout mLlHotPost;
        @Bind(R.id.tv_hot_saying)
        TextView mTvHotSaying;
        @Bind(R.id.tv_hot_likes)
        TextView mTvHotLikes;
        @Bind(R.id.tv_hot_comments)
        TextView mTvHotComments;
        @Bind(R.id.ib_new_post_avatar)
        ImageButton mIbNewPostAvatar;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
