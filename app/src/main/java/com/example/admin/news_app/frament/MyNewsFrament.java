package com.example.admin.news_app.frament;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.admin.news_app.Adapter.MyDatasAdapter;
import com.example.admin.news_app.R;
import com.example.admin.news_app.entity.NewsAPP;
import com.example.admin.news_app.utils.Constant;
import com.example.admin.news_app.utils.NoHttpUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2016-12-14.
 */

public class MyNewsFrament extends Fragment {


    private SwipeRefreshLayout sRL;
    private ListView listview;
    private List<NewsAPP.ResultBean.DataBean> datas;
    private String url;
    private MyDatasAdapter myDatasAdapter;

    public MyNewsFrament(String url) {
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sRL= (SwipeRefreshLayout) inflater.inflate(R.layout.lv_my_news_fragment_activity_main,null);
        listview = (ListView) sRL.findViewById(R.id.lv_news_fragment_activity_main);
        sRL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        myDatasAdapter.datas.remove(0);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myDatasAdapter.notifyDataSetChanged();
                                sRL.setRefreshing(false);

                            }
                        });
                    }
                }).start();
            }
        });

        return sRL;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Request<String> TopNewsStringRequest = NoHttp.createStringRequest(Constant.BASE_URL + url);

        NoHttpUtils.getInstance().add(Constant.WHAT_NEWS_REQUEST, TopNewsStringRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String result = response.get();
                NewsAPP newsAPP = JSON.parseObject(result, NewsAPP.class);
                datas = newsAPP.getResult().getData();
                myDatasAdapter = new MyDatasAdapter(getContext(), datas);
                listview.setAdapter(myDatasAdapter);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                Toast.makeText(getContext(), "response.getException():" + response.getException(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish(int what) {

            }
        });

        super.onViewCreated(view, savedInstanceState);
    }


}
