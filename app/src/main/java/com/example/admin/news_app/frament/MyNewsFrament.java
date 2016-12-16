package com.example.admin.news_app.frament;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.admin.news_app.Adapter.MyDatasAdapter;
import com.example.admin.news_app.R;
import com.example.admin.news_app.entity.NewsAPP;
import com.example.admin.news_app.utils.Constant;
import com.example.admin.news_app.utils.NoHttpUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.List;

/**
 * Created by admin on 2016-12-14.
 */

public class MyNewsFrament extends Fragment{

    private PullToRefreshListView listview;
    private List<NewsAPP.ResultBean.DataBean> datas;
    private String url;
    public  MyNewsFrament(String url){
        this.url=url;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        listview = (PullToRefreshListView) inflater.inflate(R.layout.lv_my_news_fragment_activity_main, null);
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(getContext(), "正在请求数据", Toast.LENGTH_SHORT).show();
                //结束下拉刷新，必须邮寄延迟，坑爹呀！
                listview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listview.onRefreshComplete();
                    }
                }, 4000);
            }
        });

        return listview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Request<String> TopNewsStringRequest = NoHttp.createStringRequest(Constant.BASE_URL+url);

        NoHttpUtils.getInstance().add(Constant.WHAT_NEWS_REQUEST, TopNewsStringRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String result = response.get();
                NewsAPP newsAPP = JSON.parseObject(result, NewsAPP.class);
                datas = newsAPP.getResult().getData();
                MyDatasAdapter myDatasAdapter = new MyDatasAdapter(getContext(), datas);
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
