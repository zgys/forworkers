package com.diting.zgy.forworkers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/8/6.
 */
public class Config {

    public static final String SERVER_URL="http://192.168.61.1:8080/ServerTest/api.jsp";

    public static final String KEY_TOKEN = "token";
    public static final String KEY_ACTION = "action";
    public static final String KEY_ACCOUNT_NUM = "account";
    public static final String KEY_STATUS = "status";
    public static final String KEY_PASSWD = "passwd";
    public static final String KEY_ITEMS_ORDER_TAKE = "items_order_take";
    public static final String KEY_ORDER_NUM = "order_num";
    public static final String KEY_ORDER_SERVICE_TIME = "order_service_time";
    public static final String KEY_ORDER_SITE = "order_site";
    public static final String KEY_ORDER_PROJECT = "order_project";


    public static final int RESULT_STATUS_SUCCESS=1;
    public static final int RESULT_STATUS_FAIL=0;
    public static final int RESULT_STATUS_INVALID_TOKEN=2;

    public static final String APP_ID = "com.diting.zgy";
    public static final String CHARSET = "utf-8";

    public static final String ACTION_LOGIN = "login";
    public static final String ACTION_GET_ORDER_TAKE="get_order_take";
    public static final String ACTION_GET_ORDER_CURRENT="get_order_current";
    public static final String ACTION_GET_ORDER_HISTORY="get_order_history";
    public static final String ACTION_SUBMIT_ORDER_TAKE = "submit_order_take";


    public static String getCachedToken(Context context){
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_TOKEN,null);
    }

    public static void cacheToken(Context context,String token){
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN,token);
        e.commit();
    }

    public static String getCachedAccountNum(Context context){
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_ACCOUNT_NUM,null);
    }

    public static void cacheAccountNum(Context context,String accountNum){
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
        e.putString(KEY_ACCOUNT_NUM,accountNum);
        e.commit();
    }

}
