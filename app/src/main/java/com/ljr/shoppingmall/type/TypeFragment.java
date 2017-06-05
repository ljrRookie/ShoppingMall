package com.ljr.shoppingmall.type;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LinJiaRong on 2017/5/10.
 * TODO：首页
 */

public class TypeFragment extends BaseFragment {
    private static final String TAG = TypeFragment.class.getSimpleName();
    @Bind(R.id.tablayout)
    SegmentTabLayout mTablayout;
    @Bind(R.id.iv_type_search)
    ImageView mIvTypeSearch;
    @Bind(R.id.fl_type)
    FrameLayout mFlType;
    private ArrayList<BaseFragment> mFragmentList;
    private Fragment mTempFragment;

    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        View inflate = View.inflate(mContext, R.layout.fragment_type, null);
        return inflate;
    }
/*
    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ++++++++++++++++" );
        switchFragment(mTempFragment,mFragmentList.get(0));
    }*/

    @Override
    public void initData() {
        Log.e(TAG, "initData:主页数据被初始化了 ");
        initFragment();
        String[] titles ={"分类","标签"};
        mTablayout.setTabData(titles);
        mTablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Log.e(TAG, "onTabSelect: ==================");
                switchFragment(mTempFragment,mFragmentList.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        ListFragment listFragment = new ListFragment();
        TagFragment tagFragment = new TagFragment();
        mFragmentList.add(listFragment);
        mFragmentList.add(tagFragment);
        Log.e(TAG, "initFragment: _________________" );
        switchFragment(mTempFragment, mFragmentList.get(0));
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        Log.e(TAG, "switchFragment: --------------");
        if (mTempFragment != nextFragment) {
            mTempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragemnt
                    if (fromFragment != null) {
                        ft.hide(fromFragment);
                    }
                    ft.add(R.id.fl_type, nextFragment, "tagFragment").commit();
                } else {
                    //隐藏当前Fragemnt
                    if (fromFragment != null) {
                        ft.hide(fromFragment);
                    }
                    ft.show(nextFragment).commit();
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


}
