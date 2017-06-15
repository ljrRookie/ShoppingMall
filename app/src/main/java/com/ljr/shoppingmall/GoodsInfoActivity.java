package com.ljr.shoppingmall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.home.bean.GoodsBean;
import com.ljr.shoppingmall.shoppingcart.util.CartStorage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsInfoActivity extends AppCompatActivity {

    private static final String TAG = GoodsInfoActivity.class.getSimpleName();
    @Bind(R.id.ib_good_info_back)
    ImageButton mGoodInfoBack;
    @Bind(R.id.ib_good_info_more)
    ImageButton mGoodInfoMore;
    @Bind(R.id.iv_good_info_image)
    ImageView mIvGoodInfoImage;
    @Bind(R.id.tv_good_info_name)
    TextView mTvGoodInfoName;
    @Bind(R.id.tv_good_info_desc)
    TextView mTvGoodInfoDesc;
    @Bind(R.id.tv_good_info_price)
    TextView mTvGoodInfoPrice;
    @Bind(R.id.tv_good_info_store)
    TextView mTvGoodInfoStore;
    @Bind(R.id.tv_good_info_style)
    TextView mTvGoodInfoStyle;
    @Bind(R.id.wb_good_info_more)
    WebView mWbGoodInfoMore;
    @Bind(R.id.tv_good_info_callcenter)
    TextView mTvGoodInfoCallcenter;
    @Bind(R.id.tv_good_info_collection)
    TextView mTvGoodInfoCollection;
    @Bind(R.id.tv_good_info_cart)
    TextView mTvGoodInfoCart;
    @Bind(R.id.btn_good_info_addcart)
    Button mBtnGoodInfoAddcart;
    @Bind(R.id.ll_goods_root)
    LinearLayout mLlGoodsRoot;
    @Bind(R.id.tv_more_share)
    TextView mTvMoreShare;
    @Bind(R.id.tv_more_search)
    TextView mTvMoreSearch;
    @Bind(R.id.tv_more_home)
    TextView mTvMoreHome;
    @Bind(R.id.btn_more)
    Button mBtnMore;
    @Bind(R.id.ll_root)
    LinearLayout mLlRoot;
    @Bind(R.id.activity_goods_info)
    LinearLayout mActivityGoodsInfo;
    private GoodsBean mGoodsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
        mGoodsBean = (GoodsBean) getIntent().getSerializableExtra("goodsBean");

        if (mGoodsBean != null) {
            Log.e(TAG, "onCreate: "+mGoodsBean.toString());
            setDataForView(mGoodsBean);
        }else{
            Toast.makeText(this, "没有数据！！！！", Toast.LENGTH_SHORT).show();
        }
    }

    private void setDataForView(GoodsBean goodsBean) {
        //设置图片
        Glide.with(this).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(mIvGoodInfoImage);
        //设置文本
        mTvGoodInfoName.setText(goodsBean.getName());
        //设置价格
        mTvGoodInfoPrice.setText(goodsBean.getCover_price());
        setWebViewData(goodsBean.getProduct_id());
    }

    private void setWebViewData(String product_id) {
        if (product_id != null) {
            mWbGoodInfoMore.loadUrl("https://github.com/ljrRookie");
            WebSettings settings = mWbGoodInfoMore.getSettings();
            //支持双击页面变大小
            settings.setUseWideViewPort(true);
            //支持JavaScript
            settings.setJavaScriptEnabled(true);
            //优先使用缓存
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            mWbGoodInfoMore.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制区用Webview
                    view.loadUrl(url);
                    return true;
                }
            });

        }
    }


    @OnClick({R.id.tv_good_info_callcenter, R.id.tv_good_info_collection, R.id.tv_good_info_cart,R.id.ib_good_info_back, R.id.ib_good_info_more, R.id.btn_good_info_addcart, R.id.tv_more_share, R.id.tv_more_search, R.id.tv_more_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                finish();
                break;
            case R.id.ib_good_info_more:
                Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
                if(mLlRoot.getVisibility() == View.VISIBLE){
                    mLlRoot.setVisibility(View.GONE);
                }else{
                    mLlRoot.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_good_info_addcart:
                CartStorage.getInstance().addData(mGoodsBean);
                Toast.makeText(this, "添加到成功了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_home:
                Toast.makeText(this, "主页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_callcenter:
                Toast.makeText(this, "客服", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, CallCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_good_info_collection:
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_cart:
                Toast.makeText(this, "购物车", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
