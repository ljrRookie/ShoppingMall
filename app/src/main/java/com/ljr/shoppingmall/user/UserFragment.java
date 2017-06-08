package com.ljr.shoppingmall.user;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ljr.shoppingmall.LoginActivity;
import com.ljr.shoppingmall.R;
import com.ljr.shoppingmall.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LinJiaRong on 2017/5/10.
 * TODO：首页
 */

public class UserFragment extends BaseFragment {
    private static final String TAG = UserFragment.class.getSimpleName();
    @Bind(R.id.ib_user_setting)
    ImageButton mUserSetting;
    @Bind(R.id.tv_usercenter)
    TextView mTvUsercenter;
    @Bind(R.id.ib_user_message)
    ImageButton mUserMessage;
    @Bind(R.id.ib_user_icon_avator)
    ImageButton mIbUserIconAvator;
    @Bind(R.id.tv_username)
    TextView mLogin;
    @Bind(R.id.tv_all_order)
    TextView mAllOrder;
    @Bind(R.id.tv_user_pay)
    TextView mUserPay;
    @Bind(R.id.tv_user_receive)
    TextView mUserReceive;
    @Bind(R.id.tv_user_finish)
    TextView mUserFinish;
    @Bind(R.id.tv_user_drawback)
    TextView mUserDrawback;
    @Bind(R.id.tv_user_location)
    TextView mUserLocation;
    @Bind(R.id.tv_user_collect)
    TextView mUserCollect;
    @Bind(R.id.tv_user_coupon)
    TextView mUserCoupon;
    @Bind(R.id.tv_user_prize)
    TextView mUserPrize;
    @Bind(R.id.tv_user_ticket)
    TextView mUserTicket;
    @Bind(R.id.tv_user_invitation)
    TextView mUserInvitation;
    @Bind(R.id.tv_user_callcenter)
    TextView mUserCallcenter;
    @Bind(R.id.tv_user_feedback)
    TextView mUserFeedback;
    @Bind(R.id.scrollview)
    ScrollView mScrollview;
    @Bind(R.id.user_shipments)
    TextView mUserShipments;

    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        return view;
    }

    @Override
    public void initData() {
        Log.e(TAG, "initData:主页数据被初始化了 ");
        //判断是否已经登录
        isLogin();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.ib_user_setting, R.id.ib_user_message, R.id.ib_user_icon_avator
            , R.id.tv_username, R.id.tv_all_order, R.id.tv_user_pay, R.id.tv_user_receive
            , R.id.tv_user_finish, R.id.tv_user_drawback, R.id.tv_user_location
            , R.id.tv_user_collect, R.id.tv_user_coupon, R.id.tv_user_prize
            , R.id.tv_user_ticket, R.id.tv_user_invitation, R.id.tv_user_callcenter
            , R.id.tv_user_feedback,R.id.user_shipments})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_user_setting:
                break;
            case R.id.ib_user_message:
                break;
            case R.id.ib_user_icon_avator:
                break;
            case R.id.tv_username:

                break;
            case R.id.tv_all_order:
                break;
            case R.id.tv_user_pay:
                break;
            case R.id.tv_user_receive:
                break;
            case R.id.tv_user_finish:
                break;
            case R.id.tv_user_drawback:
                break;
            case R.id.tv_user_location:
                break;
            case R.id.tv_user_collect:
                break;
            case R.id.tv_user_coupon:
                break;
            case R.id.tv_user_prize:
                break;
            case R.id.tv_user_ticket:
                break;
            case R.id.tv_user_invitation:
                break;
            case R.id.tv_user_callcenter:
                break;
            case R.id.tv_user_feedback:
                break;
            case R.id.user_shipments:
                break;
        }
    }

    /**
     * 判断是否已经登录
     */
    private void isLogin() {
        //读取本地信息
        SharedPreferences sp = this.getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");
        if(TextUtils.isEmpty(name)){
            //本地无登录信息，弹出登录框
            Login();
            Log.e(TAG, "isLogin: ===========" );
        }else{
            //直接读取用户登录信息
            loadingUsetInfo();
        }

    }

    /**
     * 直接读取用户登录信息
     */
    private void loadingUsetInfo() {
        Log.e(TAG, "loadingUsetInfo: =============" );

    }

    /**
     * 本地无登录信息，弹出登录框
     */
    private void Login() {
        new AlertDialog.Builder(this.getActivity())
                .setTitle("提示：")
                .setIcon(R.drawable.community_comment_icon)
                .setMessage("请进入登录页面")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(mContext, LoginActivity.class));
                    }
                }).setCancelable(true)
                .show();
    }


}
