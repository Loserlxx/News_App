package com.example.admin.news_app.utils;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

/**
 * Created by admin on 2016-12-13.
 *
 * NoHttpUtils工具类
 */

public class NoHttpUtils {
    private static RequestQueue instance;
    private static Object o = new Object();

    private NoHttpUtils(){

    }

    public static RequestQueue getInstance(){

        if(instance == null){

            synchronized (o){

                if(instance == null){

                    instance = NoHttp.newRequestQueue();

                }

            }

        }

        return instance;

    }

}
