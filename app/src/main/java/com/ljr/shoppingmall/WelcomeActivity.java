package com.ljr.shoppingmall;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ljr.shoppingmall.Utils.CacheUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends Activity {
    public static final String START_MAIN = "start_main";
    @Bind(R.id.iv_welcome)
    ImageView mIvWelcome;
    @Bind(R.id.rookie)
    TextView mRookie;
    @Bind(R.id.splash)
    RelativeLayout mSplash;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        //启动页---动画处理
        start();
    }

    private void start() {

        /**
         * target:代表动画执行在谁身上（找对象）
         * propertyName：动画类型（alpha：透明变化；tranlationX，translationY：平移动画；scale：缩放动画）
         * values：轨迹（选择起点和终点的变化）
         */
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mSplash, "alpha", 0f, 1f);
        objectAnimator.setDuration(1500);
        objectAnimator.start();
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationEnd(Animator animation) {
                boolean isStartMain = CacheUtils.getBoolean(WelcomeActivity.this, START_MAIN);
                if (isStartMain) {
                    mIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                } else {
                    mIntent = new Intent(WelcomeActivity.this, GuideActivity.class);
                }
                startActivity(mIntent);
                overridePendingTransition(R.anim.my_scale_action,
                        R.anim.my_alpha_action);
                finish();
            }
        });
    }
}

