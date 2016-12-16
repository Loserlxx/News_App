package com.example.admin.news_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.admin.news_app.R;
import com.example.admin.news_app.utils.CaCheUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeadActivity extends AppCompatActivity {

    @BindView(R.id.vp_lead_activity)
    ViewPager vpLeadActivity;
    @BindView(R.id.btn_enter)
    Button btnEnter;
    @BindView(R.id.ll_bottom_lead)
    LinearLayout llBottomLead;

    private int image[] = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        ButterKnife.bind(this);
        //初始化视图
        initData();
        initIndicator();
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter();
        vpLeadActivity.setAdapter(myPagerAdapter);
        //添加图片
        vpLeadActivity.setOnPageChangeListener(new MyPageChangeListener());

    }

    /**
     * 集合中添加图片
     */
    private void initData() {

        image[0] = R.mipmap.bd;
        image[1] = R.mipmap.small;
        image[2] = R.mipmap.welcome;
        image[3] = R.mipmap.wy;

    }

    /**
     * 添加下方为图片
     */
    private void initIndicator(){
        //小圆点添加
        for (int i = 0; i <image.length ; i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.shape_lead);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);

            if (i!=0){
                layoutParams.leftMargin=30;
            }else{
                view.setBackgroundResource(R.drawable.shap_color);
            }
            view.setLayoutParams(layoutParams);
            llBottomLead.addView(view);
        }
    }

    /**
     * 点击跳转第二个界面
     */
    @OnClick(R.id.btn_enter)
    public void onClick() {
        Intent intent = new Intent(LeadActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        //发送消息
        CaCheUtils.putBooleanData(LeadActivity.this,"is_first",false);
    }

    /**
     * 添加图片
     */
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(LeadActivity.this);

            imageView.setBackgroundResource(image[position]);
            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((ImageView) object);

        }
    }

    /**
     * 自定义颜色改变
     */
    class MyPageChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //使原点变色
            for (int i = 0; i < image.length; i++){


                llBottomLead.getChildAt(i).setBackgroundResource(R.drawable.shape_lead);

            }


            llBottomLead.getChildAt(position).setBackgroundResource(R.drawable.shap_color);
            //点击按钮隐藏
            if (position >= 3) {
                btnEnter.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


}
