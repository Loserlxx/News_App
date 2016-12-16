package com.example.admin.news_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.news_app.R;
import com.example.admin.news_app.utils.CaCheUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2016-12-12.
 */

public class LogoActivity extends AppCompatActivity {
    @BindView(R.id.tv_left_time_activity_logo)
    TextView tvLeftTimeActivityLogo;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.iv_logo_activity_logo)
    ImageView ivLogoActivityLogo;
    private int leftTime=3;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    if (leftTime>0){
                        String newText="广告倒计时："+leftTime-- +"秒";
                        tvLeftTimeActivityLogo.setText(newText);
                        handler.sendEmptyMessageDelayed(0,1000);
                    }else{
                        //接收信息
                        boolean is_first = CaCheUtils.getBooleanData(LogoActivity.this, "is_first", true);

                        if(is_first){
                            Intent intent = new Intent(LogoActivity.this, LeadActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Intent intent1 = new Intent(LogoActivity.this, MainActivity.class);
                            startActivity(intent1);
                            finish();
                        }

                    }
                    break;
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        ButterKnife.bind(this);
        //初始化动画
        initAnimation();
    }

    private void initAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3200);

        ivLogoActivityLogo.startAnimation(alphaAnimation);
        handler.sendEmptyMessageDelayed(0,1000);
    }
}
