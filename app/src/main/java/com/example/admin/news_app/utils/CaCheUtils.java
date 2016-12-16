package com.example.admin.news_app.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin on 2016-12-13.
 *
 * sp工具类
 * @author admin
 *
 */


public class CaCheUtils {
    private static SharedPreferences sp;
    /**
     * 往sp中添加布尔类型的数据
     * @param context
     * @param key
     * @param b
     */
    public static void putBooleanData(Context context,String key,Boolean b){

        if(sp == null){
            //初始化sp
            sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        //添加数据
        SharedPreferences.Editor edit=sp.edit();
        edit.putBoolean(key, b);
        //提交
        edit.commit();
    }
    /**
     * 往sp中添加字符串类型数据
     * @param context 上下文
     * @param key 保存的键
     * @param str 保存的值
     */
    public static void putStringData(Context context, String key, String str){

        if(sp == null){
            //初始化sp
            sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        //添加数据
        SharedPreferences.Editor edit=sp.edit();
        edit.putString(key, str);
        //提交
        edit.commit();
    }
    /**
     * 获取sp中的字符串类型数据
     * @param context 上下文
     * @param key 键
     * @param str 默认值
     * @return
     */
    public static String getStringData(Context context,String key,String str){

        if(sp == null){
            //初始化sp
            sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getString(key, str);
    }
    /**
     * 获取sp中的布尔类型数据
     * @param context 上下文
     * @param key 键
     * @param b 默认值
     * @return
     */
    public static boolean getBooleanData(Context context,String key,Boolean b){

        if(sp == null){
            //初始化sp
            sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, b);
    }

}
