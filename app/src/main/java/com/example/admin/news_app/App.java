package com.example.admin.news_app;

import android.app.Application;

import com.yolanda.nohttp.NoHttp;

import org.xutils.x;

/**
 * Created by admin on 2016-12-13.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
