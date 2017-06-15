package com.ljr.shoppingmall.community.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ljr.shoppingmall.community.HotPostFragment;
import com.ljr.shoppingmall.community.NewPostFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LinJiaRong on 2017/6/5.
 * TODO：
 */

public class CommunityViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titles = new String[]{"新帖", "热帖"};
    public CommunityViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        initFragments();
    }

    private void initFragments() {
        NewPostFragment newPostFragment = new NewPostFragment();
        HotPostFragment hotPostFragment = new HotPostFragment();
        fragmentList.add(newPostFragment);
        fragmentList.add(hotPostFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
