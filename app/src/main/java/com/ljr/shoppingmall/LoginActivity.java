package com.ljr.shoppingmall;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {
    @Bind(R.id.videoView)
    VideoView mVideoView;
    @Bind(R.id.ib_login_back)
    ImageButton mIbLoginBack;
    @Bind(R.id.username)
    EditText mUsername;
    @Bind(R.id.til_username)
    TextInputLayout mTilUsername;
    @Bind(R.id.password)
    EditText mPassword;
    @Bind(R.id.til_password)
    TextInputLayout mTilPassword;
    @Bind(R.id.login)
    Button mLogin;
    @Bind(R.id.tv_login_register)
    TextView mTvLoginRegister;
    @Bind(R.id.tv_login_forget_pwd)
    TextView mTvLoginForgetPwd;
    @Bind(R.id.ib_weibo)
    ImageButton mIbWeibo;
    @Bind(R.id.ib_qq)
    ImageButton mIbQq;
    @Bind(R.id.ib_wechat)
    ImageButton mIbWechat;
    @Bind(R.id.llayout)
    LinearLayout mLlayout;
    @Bind(R.id.home_container)
    RelativeLayout mHomeContainer;
    private MediaPlayer mp = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        final String videoPath = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.background_gif).toString();
        mVideoView.setVideoPath(videoPath);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);

            }
        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoView.setVideoPath(videoPath);
                mVideoView.start();

            }
        });
    }

    @OnClick({R.id.ib_login_back, R.id.login
            , R.id.tv_login_register, R.id.tv_login_forget_pwd, R.id.ib_weibo
            , R.id.ib_qq, R.id.ib_wechat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_login_back:
                finish();
                break;
            case R.id.login:
                login();
                break;
            case R.id.tv_login_register:

                break;
            case R.id.tv_login_forget_pwd:
                break;
            case R.id.ib_weibo:
                break;
            case R.id.ib_qq:
                break;
            case R.id.ib_wechat:
                break;
        }
    }

    private void login() {

    }
}
