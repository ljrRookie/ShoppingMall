package com.ljr.shoppingmall.community.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.Utils.BitmapUtils;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.community.bean.NewPostBean;
import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.opendanmaku.IDanmakuItem;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LinJiaRong on 2017/6/6.
 * TODO：
 */

public class NewPostAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<NewPostBean.ResultBean> datas;
    private List<String> mComment_list;

    public NewPostAdapter(Context context, List<NewPostBean.ResultBean> resultBeen) {
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
            convertView = View.inflate(mContext, R.layout.item_newpost_listview, null);
             holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        NewPostBean.ResultBean resultBean = datas.get(position);
        holder.mTvCommunityUsername.setText(resultBean.getUsername());
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        holder.mTvCommunityAddtime.setText(format.format(Integer.parseInt(resultBean.getAdd_time())));
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + resultBean.getFigure()).into(holder.mIvCommunityFigure);
        holder.mTvCommunitySaying.setText(resultBean.getSaying());
        holder.mTvCommunityLikes.setText(resultBean.getLikes());
        holder.mTvCommunityComments.setText(resultBean.getComments());
        Picasso.with(mContext).load(resultBean.getAvatar()).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                Bitmap zoom = BitmapUtils.zoom(source, 70, 70);
                Bitmap circleBitmap = BitmapUtils.circleBitmap(zoom);
                source.recycle();
                return circleBitmap;
            }

            @Override
            public String key() {
                return "";
            }
        }).into(holder.mIbNewPostAvatar);
        //设置弹幕
        mComment_list = (List<String>) resultBean.getComment_list();
        if(mComment_list!=null&&mComment_list.size() > 0){
            holder.mDanmakuView.setVisibility(View.VISIBLE);
            List<IDanmakuItem> list = new ArrayList<>();
            for(int i =0;i<mComment_list.size();i++){
                DanmakuItem danmakuItem = new DanmakuItem(mContext, mComment_list.get(i), holder.mDanmakuView.getWidth());
                list.add(danmakuItem);
            }
            Collections.shuffle(mComment_list);
            holder.mDanmakuView.addItem(list,true);
            holder.mDanmakuView.show();
        }else{
            holder.mDanmakuView.setVisibility(View.GONE);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_community_username)
        TextView mTvCommunityUsername;
        @Bind(R.id.tv_community_addtime)
        TextView mTvCommunityAddtime;
        @Bind(R.id.rl)
        RelativeLayout mRl;
        @Bind(R.id.iv_community_figure)
        ImageView mIvCommunityFigure;
        @Bind(R.id.danmakuView)
        DanmakuView mDanmakuView;
        @Bind(R.id.tv_community_saying)
        TextView mTvCommunitySaying;
        @Bind(R.id.tv_community_likes)
        TextView mTvCommunityLikes;
        @Bind(R.id.tv_community_comments)
        TextView mTvCommunityComments;
        @Bind(R.id.ib_new_post_avatar)
        ImageButton mIbNewPostAvatar;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
