package com.ljr.shoppingmall;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ljr.shoppingmall.base.BaseFragment;
import com.ljr.shoppingmall.community.CommunityFragment;
import com.ljr.shoppingmall.home.HomeFragment;
import com.ljr.shoppingmall.shoppingcart.ShoppingCartFragment;
import com.ljr.shoppingmall.type.TypeFragment;
import com.ljr.shoppingmall.user.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.frameLayout)
    FrameLayout mFrameLayout;
    @Bind(R.id.rb_home)
    RadioButton mRbHome;
    @Bind(R.id.rb_type)
    RadioButton mRbType;
    @Bind(R.id.rb_community)
    RadioButton mRbCommunity;
    @Bind(R.id.rb_cart)
    RadioButton mRbCart;
    @Bind(R.id.rb_user)
    RadioButton mRbUser;
    @Bind(R.id.rg_main)
    RadioGroup mRgMain;
    private ArrayList<BaseFragment> mFragments;
    private int mPosition;//fragmentID
    private Fragment mTempFragment;//当前Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragments();
        initListener();
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new TypeFragment());
        mFragments.add(new CommunityFragment());
        mFragments.add(new ShoppingCartFragment());
        mFragments.add(new UserFragment());
    }

    private void initListener() {
        mRgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        mPosition = 0;
                        break;
                    case R.id.rb_type:
                        mPosition = 1;
                        break;
                    case R.id.rb_community:
                        mPosition = 2;
                        break;
                    case R.id.rb_cart:
                        mPosition = 3;
                        break;
                    case R.id.rb_user:
                        mPosition = 4;
                        break;
                }
                BaseFragment baseFragment = getFragment(mPosition);
                swithFragment(mTempFragment, baseFragment);
            }
        });
        mRgMain.check(R.id.rb_home);
    }

    private BaseFragment getFragment(int position) {
        if (mFragments != null && mFragments.size() > 0) {
            BaseFragment baseFragment = mFragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void swithFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (fromFragment != nextFragment) {
            mTempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    if (fromFragment != null) {
                        //隐藏当前Fragment
                        ft.hide(fromFragment);
                    }
                    ft.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if(fromFragment != null){
                        ft.hide(fromFragment);
                    }
                    ft.show(nextFragment).commit();
                }
            }
        }
    }
}
