package com.ljr.shoppingmall.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ljr.shoppingmall.GoodsInfoActivity;
import com.ljr.shoppingmall.MainActivity;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.Utils.SpaceItemDecoration;
import com.ljr.shoppingmall.base.Constants;
import com.ljr.shoppingmall.home.adapter.GoodsListAdapter;
import com.ljr.shoppingmall.home.bean.GoodsBean;
import com.ljr.shoppingmall.home.bean.TypeListBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class GoodsListActivity extends AppCompatActivity {

    @Bind(R.id.ib_goods_list_back)
    ImageButton mIbGoodsListBack;
    @Bind(R.id.tv_goods_list_search)
    TextView mTvGoodsListSearch;
    @Bind(R.id.ib_goods_list_home)
    ImageButton mIbGoodsListHome;
    @Bind(R.id.tv_goods_list_sort)
    TextView mTvGoodsListSort;
    @Bind(R.id.tv_goods_list_price)
    TextView mTvGoodsListPrice;
    @Bind(R.id.iv_goods_list_arrow)
    ImageView mIvGoodsListArrow;
    @Bind(R.id.ll_goods_list_price)
    LinearLayout mLlGoodsListPrice;
    @Bind(R.id.tv_goods_list_select)
    TextView mTvGoodsListSelect;
    @Bind(R.id.ll_goods_list_head)
    LinearLayout mLlGoodsListHead;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @Bind(R.id.ib_drawer_layout_back)
    ImageButton mIbDrawerLayoutBack;
    @Bind(R.id.tv_ib_drawer_layout_title)
    TextView mTvIbDrawerLayoutTitle;
    @Bind(R.id.ib_drawer_layout_confirm)
    TextView mIbDrawerLayoutConfirm;
    @Bind(R.id.rb_select_hot)
    RadioButton mRbSelectHot;
    @Bind(R.id.rb_select_new)
    RadioButton mRbSelectNew;
    @Bind(R.id.rg_select)
    RadioGroup mRgSelect;
    @Bind(R.id.tv_drawer_price)
    TextView mTvDrawerPrice;

    @Bind(R.id.tv_drawer_recommend)
    TextView mTvDrawerRecommend;
    @Bind(R.id.rl_select_recommend_theme)
    RelativeLayout mRlSelectRecommendTheme;
    @Bind(R.id.tv_drawer_type)
    TextView mTvDrawerType;
    @Bind(R.id.rl_select_type)
    RelativeLayout mRlSelectType;
    @Bind(R.id.btn_select_all)
    Button mBtnSelectAll;
    @Bind(R.id.ll_select_root)
    LinearLayout mLlSelectRoot;
    @Bind(R.id.btn_drawer_layout_cancel)
    Button mBtnDrawerLayoutCancel;
    @Bind(R.id.btn_drawer_layout_confirm)
    Button mBtnDrawerLayoutConfirm;
    @Bind(R.id.iv_price_no_limit)
    ImageView mIvPriceNoLimit;
    @Bind(R.id.rl_price_nolimit)
    RelativeLayout mRlPriceNolimit;
    @Bind(R.id.iv_price_0_15)
    ImageView mIvPrice015;
    @Bind(R.id.rl_price_0_15)
    RelativeLayout mRlPrice015;
    @Bind(R.id.iv_price_15_30)
    ImageView mIvPrice1530;
    @Bind(R.id.rl_price_15_30)
    RelativeLayout mRlPrice1530;
    @Bind(R.id.iv_price_30_50)
    ImageView mIvPrice3050;
    @Bind(R.id.rl_price_30_50)
    RelativeLayout mRlPrice3050;
    @Bind(R.id.iv_price_50_70)
    ImageView mIvPrice5070;
    @Bind(R.id.rl_price_50_70)
    RelativeLayout mRlPrice5070;
    @Bind(R.id.iv_price_70_100)
    ImageView mIvPrice70100;
    @Bind(R.id.rl_price_70_100)
    RelativeLayout mRlPrice70100;
    @Bind(R.id.iv_price_100)
    ImageView mIvPrice100;
    @Bind(R.id.rl_price_100)
    RelativeLayout mRlPrice100;
    @Bind(R.id.et_price_start)
    EditText mEtPriceStart;
    @Bind(R.id.v_price_line)
    View mVPriceLine;
    @Bind(R.id.et_price_end)
    EditText mEtPriceEnd;
    @Bind(R.id.rl_select_price)
    RelativeLayout mRlSelectPrice;
    @Bind(R.id.ll_price_root)
    LinearLayout mLlPriceRoot;
    @Bind(R.id.btn_drawer_theme_cancel)
    Button mBtnDrawerThemeCancel;
    @Bind(R.id.btn_drawer_theme_confirm)
    Button mBtnDrawerThemeConfirm;
    @Bind(R.id.iv_theme_all)
    ImageView mIvThemeAll;
    @Bind(R.id.rl_theme_all)
    RelativeLayout mRlThemeAll;
    @Bind(R.id.iv_theme_note)
    ImageView mIvThemeNote;
    @Bind(R.id.rl_theme_note)
    RelativeLayout mRlThemeNote;
    @Bind(R.id.iv_theme_funko)
    ImageView mIvThemeFunko;
    @Bind(R.id.rl_theme_funko)
    RelativeLayout mRlThemeFunko;
    @Bind(R.id.iv_theme_gsc)
    ImageView mIvThemeGsc;
    @Bind(R.id.rl_theme_gsc)
    RelativeLayout mRlThemeGsc;
    @Bind(R.id.iv_theme_origin)
    ImageView mIvThemeOrigin;
    @Bind(R.id.rl_theme_origin)
    RelativeLayout mRlThemeOrigin;
    @Bind(R.id.iv_theme_sword)
    ImageView mIvThemeSword;
    @Bind(R.id.rl_theme_sword)
    RelativeLayout mRlThemeSword;
    @Bind(R.id.iv_theme_food)
    ImageView mIvThemeFood;
    @Bind(R.id.rl_theme_food)
    RelativeLayout mRlThemeFood;
    @Bind(R.id.iv_theme_moon)
    ImageView mIvThemeMoon;
    @Bind(R.id.rl_theme_moon)
    RelativeLayout mRlThemeMoon;
    @Bind(R.id.iv_theme_quanzhi)
    ImageView mIvThemeQuanzhi;
    @Bind(R.id.rl_theme_quanzhi)
    RelativeLayout mRlThemeQuanzhi;
    @Bind(R.id.iv_theme_gress)
    ImageView mIvThemeGress;
    @Bind(R.id.rl_theme_gress)
    RelativeLayout mRlThemeGress;
    @Bind(R.id.ll_theme_root)
    LinearLayout mLlThemeRoot;
    @Bind(R.id.btn_drawer_type_cancel)
    Button mBtnDrawerTypeCancel;
    @Bind(R.id.btn_drawer_type_confirm)
    Button mBtnDrawerTypeConfirm;
    @Bind(R.id.expandableListView)
    ExpandableListView mExpandableListView;
    @Bind(R.id.ll_type_root)
    LinearLayout mLlTypeRoot;
    @Bind(R.id.dl_left)
    DrawerLayout mDlLeft;
    @Bind(R.id.rl_drawer_price)
    RelativeLayout mRlDrawerPrice;

    private String[] urls = new String[]{
            Constants.CLOSE_STORE,
            Constants.GAME_STORE,
            Constants.COMIC_STORE,
            Constants.COSPLAY_STORE,
            Constants.GUFENG_STORE,
            Constants.STICK_STORE,
            Constants.WENJU_STORE,
            Constants.FOOD_STORE,
            Constants.SHOUSHI_STORE,
    };
    private int click_count = 0;
    private int mPosition;
    private static final String TAG = "GoodsListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mPosition = intent.getIntExtra("position", -1);
        //获取数据
        getDataFromNet();
    }

    public void getDataFromNet() {
        OkHttpUtils.get().url(urls[mPosition]).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "onError: 联网失败"+e.getMessage());

            }

            @Override
            public void onResponse(String response, int id) {
    if(response != null){
        List<TypeListBean.ResultBean.PageDataBean> pageDataBeen = processData(response);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(GoodsListActivity.this, 2);
        mRecyclerview.setLayoutManager(gridLayoutManager);
        GoodsListAdapter goodsListAdapter = new GoodsListAdapter(GoodsListActivity.this, pageDataBeen);
       // mRecyclerview.addItemDecoration(new DividerItemDecoration(GoodsListActivity.this,gridLayoutManager.getOrientation()));
        mRecyclerview.addItemDecoration(new SpaceItemDecoration(10));
        mRecyclerview.setAdapter(goodsListAdapter);
        goodsListAdapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(TypeListBean.ResultBean.PageDataBean dataBean) {
                String name = dataBean.getName();
                String cover_price = dataBean.getCover_price();
                String figure = dataBean.getFigure();
                String product_id = dataBean.getProduct_id();
                GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);
                Intent intent = new Intent(GoodsListActivity.this, GoodsInfoActivity.class);
                intent.putExtra("goodsBean",goodsBean);
                startActivity(intent);

            }
        });
    }
            }
        });
    }

    private List<TypeListBean.ResultBean.PageDataBean> processData(String response) {
        Gson gson = new Gson();
        TypeListBean typeListBean = gson.fromJson(response, TypeListBean.class);
        List<TypeListBean.ResultBean.PageDataBean> page_data = typeListBean.getResult().getPage_data();
        return page_data;
    }


    @OnClick({R.id.ib_goods_list_back, R.id.ib_goods_list_home, R.id.tv_goods_list_sort
            , R.id.tv_goods_list_price, R.id.tv_goods_list_select, R.id.ib_drawer_layout_back
            , R.id.ib_drawer_layout_confirm, R.id.rb_select_hot, R.id.rb_select_new
            , R.id.tv_drawer_price, R.id.tv_drawer_recommend, R.id.tv_drawer_type
            , R.id.btn_select_all, R.id.btn_drawer_layout_cancel, R.id.btn_drawer_layout_confirm
            , R.id.rl_price_nolimit, R.id.rl_price_0_15, R.id.rl_price_15_30, R.id.rl_price_30_50
            , R.id.rl_price_50_70, R.id.rl_price_70_100, R.id.rl_price_100, R.id.rl_select_price
            , R.id.btn_drawer_theme_cancel, R.id.btn_drawer_theme_confirm, R.id.rl_theme_all
            , R.id.rl_theme_note, R.id.rl_theme_funko, R.id.rl_theme_gsc, R.id.rl_theme_origin
            , R.id.rl_theme_sword, R.id.rl_theme_food, R.id.rl_theme_moon, R.id.rl_theme_quanzhi
            , R.id.rl_theme_gress, R.id.btn_drawer_type_cancel, R.id.btn_drawer_type_confirm
            , R.id.rl_drawer_price, R.id.rl_select_recommend_theme, R.id.rl_select_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_goods_list_back:
                finish();
                break;
            case R.id.ib_goods_list_home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Constants.isBackHome = true;
                break;
            //综合排序点击事件
            case R.id.tv_goods_list_sort:
                click_count = 0;
                mIvGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_normal);
                mTvGoodsListPrice.setTextColor(Color.parseColor("#333538"));
                mTvGoodsListSort.setTextColor(Color.parseColor("#ed4141"));
                break;
            //价格点击事件
            case R.id.tv_goods_list_price:
                click_count++;
                //综合排序变为默认颜色
                mTvGoodsListSort.setTextColor(Color.parseColor("#333538"));
                //价格变为红色
                mTvGoodsListPrice.setTextColor(Color.parseColor("#ed4141"));
                if (click_count % 2 == 1) {
                    //箭头向下红色
                    mIvGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_desc);
                } else {
                    //箭头向上红色
                    mIvGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_asc);
                }
                break;
            //筛选点击事件
            case R.id.tv_goods_list_select:
                mTvGoodsListSelect.setTextColor(Color.parseColor("#ed4141"));
                mDlLeft.openDrawer(Gravity.RIGHT);
                break;
            //价格筛选页面
            case R.id.rl_drawer_price:
                mLlPriceRoot.setVisibility(View.VISIBLE);
                mIbDrawerLayoutBack.setVisibility(View.GONE);
                break;
            case R.id.rl_select_recommend_theme:
                break;
            case R.id.rl_select_type:
                break;
            case R.id.ib_drawer_layout_back:
                mDlLeft.closeDrawers();
                break;
            case R.id.ib_drawer_layout_confirm:
                break;
            case R.id.rb_select_hot:
                break;
            case R.id.rb_select_new:
                break;
            case R.id.tv_drawer_price:
                break;
            case R.id.tv_drawer_recommend:
                break;
            case R.id.tv_drawer_type:
                break;
            case R.id.btn_select_all:
                break;
            case R.id.btn_drawer_layout_cancel:
                mLlPriceRoot.setVisibility(View.GONE);

                break;
            case R.id.btn_drawer_layout_confirm:
                break;
            case R.id.rl_price_nolimit:
                break;
            case R.id.rl_price_0_15:
                break;
            case R.id.rl_price_15_30:
                break;
            case R.id.rl_price_30_50:
                break;
            case R.id.rl_price_50_70:
                break;
            case R.id.rl_price_70_100:
                break;
            case R.id.rl_price_100:
                break;
            case R.id.rl_select_price:
                break;
            case R.id.btn_drawer_theme_cancel:
                finish();
                break;
            case R.id.btn_drawer_theme_confirm:
                break;
            case R.id.rl_theme_all:
                break;
            case R.id.rl_theme_note:
                break;
            case R.id.rl_theme_funko:
                break;
            case R.id.rl_theme_gsc:
                break;
            case R.id.rl_theme_origin:
                break;
            case R.id.rl_theme_sword:
                break;
            case R.id.rl_theme_food:
                break;
            case R.id.rl_theme_moon:
                break;
            case R.id.rl_theme_quanzhi:
                break;
            case R.id.rl_theme_gress:
                break;
            case R.id.btn_drawer_type_cancel:
                finish();
                break;
            case R.id.btn_drawer_type_confirm:
                break;
        }
    }
}
