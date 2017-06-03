package com.ljr.shoppingmall;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ljr.shoppingmall.Utils.CacheUtils;
import com.ljr.shoppingmall.Utils.DensityUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends Activity {

    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.btn_start_main)
    Button mBtnStartMain;
    @Bind(R.id.ll_point_group)
    LinearLayout mLlPointGroup;
    @Bind(R.id.iv_red_point)
    ImageView mIvRedPoint;
    //指导页图片
    int[] ids = new int[]{R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};
    private ArrayList<ImageView> mImageViews;
    private int mWidthdpi;
    private int mLeftmax;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mImageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            mImageViews.add(imageView);
            //创建点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            /**
             * 屏幕适配：
             * 单位是像素
             * 把单位dp转成对应的像素
             */
            mWidthdpi = DensityUtil.dip2px(this, 10);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mWidthdpi, mWidthdpi);
            if (i != 0) {
                //不包括第0个，所有的点距离左边有10个像素
                params.leftMargin = mWidthdpi;
            }
            point.setLayoutParams(params);
            //添加到线性布局里面
            mLlPointGroup.addView(point);
        }
        //设置ViewPager的适配器
        mViewpager.setAdapter(new MyPagerAdapter());
        //根据View的生命周期，当视图执行到onLayout或者onDraw的时候，视图的高和宽，边距都有了
        mIvRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlolLayoutListener());
        //得到屏幕滑动的百分比
        mViewpager.addOnPageChangeListener(new MyOnPageChangeListener());

    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        /**
         * 当页面回调了会调用此方法
         *
         * @param position             当前滑动页面的位置
         * @param positionOffset       页面滑动的百分比
         * @param positionOffsetPixels 滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //两点间移动的距离 = 屏幕滑动百分比 + 两点间移动的距离
            //两点间滑动距离对应的坐标 = 原来的起始位置 + 两点间移动的距离
            int leftMargin = (int) (position * mLeftmax + (positionOffset * mLeftmax));
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mIvRedPoint.getLayoutParams();
            params.leftMargin = leftMargin;
            mIvRedPoint.setLayoutParams(params);
        }

        /**
         * 单页面被选中的时候，回调这个方法
         *
         * @param position 被选中页面的对应的位置
         */
        @Override
        public void onPageSelected(int position) {
            if (position == mImageViews.size() - 1) {
                mBtnStartMain.setVisibility(View.VISIBLE);
            } else {
                mBtnStartMain.setVisibility(View.GONE);
            }
        }

        /**
         * 当ViewPager页面滑动状态发生变化的时候回调此方法
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    private class MyOnGlolLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            //此方法会调用两次，执行一次之后移除稍微提高一点点性能
            mIvRedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(MyOnGlolLayoutListener.this);
            //间距 = 第1个点距离左边的距离 - 第0个点距离左边的距离
            mLeftmax = mLlPointGroup.getChildAt(1).getLeft() - mLlPointGroup.getChildAt(0).getLeft();
        }

    }

    @OnClick(R.id.btn_start_main)
    public void onClick() {
        CacheUtils.putBoolean(GuideActivity.this, WelcomeActivity.START_MAIN,true);
        startActivity(new Intent(GuideActivity.this,MainActivity.class));
        overridePendingTransition(R.anim.slide_left,
                R.anim.slide_right);
        finish();
    }

    private class MyPagerAdapter extends PagerAdapter {

        /**
         * 返回数据的总个数
         *
         * @return
         */
        @Override
        public int getCount() {
            return mImageViews.size();
        }

        /**
         * getView()
         *
         * @param container ViewPager
         * @param position  要创建的页面位置
         * @return 放回和创建当前页面右关系的值
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mImageViews.get(position);
            //添加到容器里
            container.addView(imageView);
            return imageView;
        }

        /**
         * 判断
         *
         * @param view   当前创建的视图
         * @param object instantiateItem放回的结果值
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 销毁页面
         *
         * @param container ViewPager
         * @param position  要销毁的页面位置
         * @param object    要销毁的页面
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
