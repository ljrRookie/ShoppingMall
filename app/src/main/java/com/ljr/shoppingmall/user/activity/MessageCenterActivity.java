package com.ljr.shoppingmall.user.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.ljr.shoppingmall.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageCenterActivity extends AppCompatActivity {

    @Bind(R.id.ib_login_back)
    ImageButton mIbLoginBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ib_login_back)
    public void onClick() {
        finish();
    }
}
