package com.example.admin.news_app.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.news_app.R;
import com.example.admin.news_app.entity.NewsAPP;
import com.example.admin.news_app.utils.Constant;
import com.example.admin.news_app.utils.NoHttpUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.xutils.x;

import java.util.List;

/**
 * Created by admin on 2016-12-14.
 */

public class MyDatasAdapter extends BaseAdapter {
    private List<NewsAPP.ResultBean.DataBean> datas;
    private Context context;

    public MyDatasAdapter(Context context, List<NewsAPP.ResultBean.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View ContentView, ViewGroup viewGroup) {
        View view = null;
        ViewHolder holder = null;
        if (ContentView == null) {
            holder = new ViewHolder();
            view=View.inflate(context, R.layout.listview_my_datas_fragment,null);
            holder.mIv_icon= (ImageView) view.findViewById(R.id.iv_icon_my_datas_fragment_activity_main);
            holder.mTv_date= (TextView) view.findViewById(R.id.tv_date_my_datas_fragment_activity_main);
            holder.mTv_title= (TextView) view.findViewById(R.id.tv_title_my_datas_fragment_activity_main);
            view.setTag(holder);
        }else{
            view=ContentView;
            holder= (ViewHolder) view.getTag();
        }

        //初始化数据
        //用NoHttp进行获取图片
        /*final ImageView imageView = holder.mIv_icon;
        Request<Bitmap> imageRequest = NoHttp.createImageRequest(datas.get(i).getThumbnail_pic_s());

        NoHttpUtils.getInstance().add(Constant.WHAT_NEWS_REQUEST, imageRequest, new OnResponseListener<Bitmap>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<Bitmap> response) {
                imageView.setImageBitmap(response.get());
            }

            @Override
            public void onFailed(int what, Response<Bitmap> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });*/
        //用glibe RGB 456
        Glide.with(context).load(datas.get(i).getThumbnail_pic_s()).crossFade().into(holder.mIv_icon);
        holder.mTv_title.setText(datas.get(i).getTitle());
        holder.mTv_date.setText(datas.get(i).getDate());



        return view;
    }

    class ViewHolder {
        ImageView mIv_icon;
        TextView mTv_title;
        TextView mTv_date;
    }


}
