package com.ljr.shoppingmall.community;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.base.BaseFragment;
import com.ljr.shoppingmall.community.adapter.CommunityViewPagerAdapter;

/**
 * Created by LinJiaRong on 2017/5/10.
 * TODO：发现
 */

public class CommunityFragment extends BaseFragment {
    private static final String TAG = CommunityFragment.class.getSimpleName();
    private ImageButton ibCommunityIcon;
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ImageButton ibCommunityMessage;

    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_community, null);
        ibCommunityIcon = (ImageButton) view.findViewById(R.id.ib_community_icon);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        ibCommunityMessage = (ImageButton) view.findViewById(R.id.ib_community_message);

        CommunityViewPagerAdapter communityViewPagerAdapter = new CommunityViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(communityViewPagerAdapter);
        tablayout.setVisibility(View.VISIBLE);
        tablayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void initData() {
        Log.e(TAG, "initData:主页数据被初始化了 ");

    }
}
