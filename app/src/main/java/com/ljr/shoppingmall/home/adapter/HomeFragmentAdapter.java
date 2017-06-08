package com.ljr.shoppingmall.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ljr.shoppingmall.GoodsInfoActivity;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.home.activity.GoodsListActivity;
import com.ljr.shoppingmall.home.bean.GoodsBean;
import com.ljr.shoppingmall.home.bean.ResultBeanData;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.youth.banner.transformer.ScaleInOutTransformer;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by LinJiaRong on 2017/5/31.
 * TODO：
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    /**
     * 广告条幅类型
     */
    public static final int BANNER = 0;

    /**
     * 频道类型
     */
    public static final int CHANNEL = 1;
    /**
     * 活动类型
     */
    public static final int ACT = 2;
    /**
     * 秒杀类型
     */
    public static final int SECKILL = 3;
    /**
     * 推荐类型
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;
    private static final String TAG = HomeFragmentAdapter.class.getSimpleName();
    private static final String GOODS_BEAN = "goodsBean";
    private ResultBeanData.ResultBean mResultBean;
    private Context mContext;
    /**
     * 当前类型
     */
    private int currentType = BANNER;
    private LayoutInflater mLayoutInflater;

    public HomeFragmentAdapter(Context context, ResultBeanData.ResultBean resultBean) {
        this.mContext = context;
        this.mResultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 相当于getView 创建ViewHolder部分代码
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item, null));
        } else if (viewType == HOT) {
            return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, null));
        }

        return null;
    }

    /**
     * 相当于getview中的绑定数据模块
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(mResultBean.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(mResultBean.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(mResultBean.getAct_info());
        } else if(getItemViewType(position) == SECKILL){
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(mResultBean.getSeckill_info());
        }else if(getItemViewType(position) == RECOMMEND){
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(mResultBean.getRecommend_info());
        }else if(getItemViewType(position)==HOT){
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(mResultBean.getHot_info());
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    /**
     * 得到类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

     class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private Banner mBanner;

        public BannerViewHolder(Context context, View inflate) {
            super(inflate);
            this.mContext = context;
            this.mBanner = (Banner) inflate.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = banner_info.get(i).getImage();

                imagesUrl.add(imageUrl);
            }
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            mBanner.setBannerAnimation(Transformer.Accordion);
            mBanner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //联网请求图片-Glide
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE + url).into(view);
                }
            });

            mBanner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "positiong=="+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView mChannel;

        public ChannelViewHolder(Context context, View inflate) {
            super(inflate);
            this.mContext = context;
            mChannel = (GridView) inflate.findViewById(R.id.gv_channel);

        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            ChannelAdapter channelAdapter = new ChannelAdapter(mContext, channel_info);
            mChannel.setAdapter(channelAdapter);
            mChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(position <= 8){
                        Intent intent = new Intent(mContext, GoodsListActivity.class);
                        intent.putExtra("position",position);
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }

     class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager mActViewpager;

        public ActViewHolder(Context context, View inflate) {
            super(inflate);
            this.mContext = context;
            mActViewpager = (ViewPager) inflate.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            mActViewpager.setPageMargin(20);
            mActViewpager.setOffscreenPageLimit(3);
            //setPageTransformer 决定动画效果
            mActViewpager.setPageTransformer(true, new ScaleInOutTransformer());
            mActViewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                /**
                 *
                 * @param container   ViewPager
                 * @param position    对应页面的位置
                 * @return
                 */
                @Override
                public Object instantiateItem(ViewGroup container, final int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE + act_info.get(position).getIcon_url()).into(imageView);
                   /* Log.e(TAG, "instantiateItem: "+act_info.get(position).getIcon_url());*/
                    container.addView(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }

     class SeckillViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView mTvtime;
        private TextView mTvMore;
        private RecyclerView mRvSeckill;
        /**
         * 相差多少时间-毫秒
         */
        private long dt = 0;
         private Handler mHandler = new Handler(){
             @Override
             public void handleMessage(Message msg) {
                 super.handleMessage(msg);
                 dt = dt - 1000;
                 SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                 String time = format.format(new Date(dt));
                 mTvtime.setText(time);

                 mHandler.removeMessages(0);
                 mHandler.sendEmptyMessageDelayed(0,1000);
                 if(dt <=0){
                     //把消息移除
                     mHandler.removeCallbacksAndMessages(null);
                 }
             }
         };
        public SeckillViewHolder(Context context, View inflate) {
            super(inflate);
            this.mContext = context;
            mTvtime = (TextView) itemView.findViewById(R.id.tv_time_seckill);
            mTvMore = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            mRvSeckill = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
        }

        public void setData(final ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
            SeckillRecyclerViewAdapter seckillRecyclerViewAdapter = new SeckillRecyclerViewAdapter(mContext, seckill_info.getList());
            mRvSeckill.setAdapter(seckillRecyclerViewAdapter);
            mRvSeckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            seckillRecyclerViewAdapter.setOnSeckillRecyclerView(new SeckillRecyclerViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                  //  Toast.makeText(mContext, "秒杀="+position, Toast.LENGTH_SHORT).show();
                    ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = seckill_info.getList().get(position);
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setProduct_id(listBean.getProduct_id());
                    goodsBean.setCover_price(listBean.getCover_price());
                    goodsBean.setFigure(listBean.getFigure());
                    goodsBean.setName(listBean.getName());
                    Log.e(TAG, "onItemClick: "+ goodsBean.toString());

                    startGoodsInfoActivity(goodsBean);

                }
            });
//设置秒杀时间
            dt = Integer.valueOf(seckill_info.getEnd_time()) - Integer.valueOf(seckill_info.getStart_time());
            mHandler.sendEmptyMessageDelayed(0,1000);
        }
    }

     class RecommendViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView mTvMoreRecommend;
        private GridView mGvRecommend;
        private RecommendGridViewAdapter mRecommendGridViewAdapter;

        public RecommendViewHolder(Context context, View inflate) {
            super(inflate);
            this.mContext = context;
            mTvMoreRecommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            mGvRecommend = (GridView) itemView.findViewById(R.id.gv_recommend);

        }

        public void setData(final List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            mRecommendGridViewAdapter = new RecommendGridViewAdapter(mContext, recommend_info);
            mGvRecommend.setAdapter(mRecommendGridViewAdapter);
            mGvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  //  Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                    ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean =recommend_info.get(position);
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

     class HotViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private TextView mTvMoreHot;
        private GridView mGvHot;
        private HotGridViewAdapter mHotGridViewAdapter;

        public HotViewHolder(Context context, View inflate) {
            super(inflate);
            this.mContext = context;
            mTvMoreHot = (TextView) itemView.findViewById(R.id.tv_more_hot);
            mGvHot = (GridView) itemView.findViewById(R.id.gv_hot);
        }

        public void setData(final List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
            mHotGridViewAdapter = new HotGridViewAdapter(mContext, hot_info);
            mGvHot.setAdapter(mHotGridViewAdapter);
            mGvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   // Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                    ResultBeanData.ResultBean.HotInfoBean hotInfoBean = hot_info.get(position);
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setFigure(hotInfoBean.getFigure());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsBean);
        mContext.startActivity(intent);
    }
}
