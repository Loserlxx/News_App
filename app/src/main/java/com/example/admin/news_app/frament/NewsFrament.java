package com.example.admin.news_app.frament;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.admin.news_app.Adapter.MyNewsAdapter;
import com.example.admin.news_app.R;
import com.example.admin.news_app.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2016-12-14.
 */

public class NewsFrament extends Fragment {



    TabLayout tabs;

    ViewPager viewPager;
    /*@BindView(R.id.tabs_fragment_activity_main)
          PagerTabStrip tabsFragmentActivityMain;*/
    private List<Fragment> fragments;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.layout_news_fragment_activity_main, null);
        viewPager = (ViewPager) ll.findViewById(R.id.vp_news_fragment);
        tabs = (TabLayout) ll.findViewById(R.id.tabs_news_fragment_activity_main);

        //关联viewPager
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setBackgroundColor(Color.argb(20,0,0,150));

        return ll;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragments = new ArrayList<>();
        fragments.add(new MyNewsFrament(Constant.TOP_NEWS_QUERY_STRING));
        fragments.add(new MyNewsFrament(Constant.SHEHUI_NEWS_QUERY_STRING));
        fragments.add(new MyNewsFrament(Constant.GUONEI_NEWS_QUERY_STRING));
        fragments.add(new MyNewsFrament(Constant.GUOJI_NEWS_QUERY_STRING));
        fragments.add(new MyNewsFrament(Constant.YULE_NEWS_QUERY_STRING));
        fragments.add(new MyNewsFrament(Constant.TIYU_NEWS_QUERY_STRING));
        fragments.add(new MyNewsFrament(Constant.JUNSHI_NEWS_QUERY_STRING));
        fragments.add(new MyNewsFrament(Constant.KEJI_NEWS_QUERY_STRING));
        fragments.add(new MyNewsFrament(Constant.CAIJING_NEWS_QUERY_STRING));
        fragments.add(new MyNewsFrament(Constant.SHISHANG_NEWS_QUERY_STRING));
        MyNewsAdapter myNewsAdapter = new MyNewsAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(myNewsAdapter);
       /* tabsFragmentActivityMain.setBackgroundColor(Color.argb(30,0,0,80));
        tabsFragmentActivityMain.setTextColor(Color.BLACK);*/
        /*Request<String> stringRequest = NoHttp.createStringRequest(Constant.BASE_URL + Constant.TOP_NEWS_QUERY_STRING);

        NoHttpUtils.getInstance().add(Constant.WHAT_NEWS_REQUEST, stringRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String result = response.get();
                NewsAPP newsAPP = JSON.parseObject(result, NewsAPP.class);
                Toast.makeText(getContext(), newsAPP.getResult().getData().get(1).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                Toast.makeText(getContext(), "response.getException():"+response.getException(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish(int what) {

            }
        });
*/
    }
}
