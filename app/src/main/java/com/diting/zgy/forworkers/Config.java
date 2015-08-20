package com.diting.zgy.forworkers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/8/6.
 */
public class Config {

    public static final String SERVER_URL="http://192.168.199.215:8080/ServerTest/api.jsp";

    public static final String KEY_TOKEN = "token";
    public static final String KEY_ACTION = "action";
    public static final String KEY_ACCOUNT_NUM = "account";
    public static final String KEY_STATUS = "status";
    public static final String KEY_PASSWD = "passwd";
    public static final String KEY_ITEMS_ORDER_TAKE = "items_order_take";
    public static final String KEY_ITEMS_ORDER_CURRENT = "items_order_current";
    public static final String KEY_ITEMS_ORDER_HISTORY = "items_order_history";
    public static final String KEY_ORDER_NUM = "order_num";
    public static final String KEY_ORDER_SERVICE_TIME = "order_service_time";
    public static final String KEY_ORDER_SITE = "order_site";
    public static final String KEY_ORDER_PROJECT = "order_project";
    public static final String KEY_ORDER_STATUS = "order_status";
    public static final String KEY_REST_STATUS = "rest_status";
    public static final String KEY_REPAIR_NUM = "repair_num";
    public static final String KEY_REPAIR_PRICE = "repair_price";
    public static final String KEY_REPAIR_DESCRIPTION = "repair_description";
    public static final String KEY_REPAIR_NAME = "repair_name";


    public static final int RESULT_STATUS_SUCCESS=1;
    public static final int RESULT_STATUS_FAIL=0;
    public static final int RESULT_STATUS_INVALID_TOKEN=2;

    public static final int STUFF_WORK_REQUEST =1;
    public static final int STUFF_REST_REQUEST =0;

    public static final String APP_ID = "com.diting.zgy";
    public static final String CHARSET = "utf-8";

    public static final String ACTION_LOGIN = "login";
    public static final String ACTION_GET_ORDER_TAKE="get_order_take";
    public static final String ACTION_GET_ORDER_CURRENT="get_order_current";
    public static final String ACTION_GET_ORDER_HISTORY="get_order_history";
    public static final String ACTION_SUBMIT_ORDER_TAKE = "submit_order_take";
    public static final String ACTION_SUBMIT_ORDER_IGNORE = "submit_order_ignore";
    public static final String ACTION_REST_REQUEST = "submit_rest_request";
    public static final String ACTION_WORK_REQUEST = "submit_work_request";
    public static final String ACTION_GET_REPAIR_ITEMS = "get_repair_items";

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

    public static boolean getCachedRestStatus(Context context){
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getBoolean(KEY_REST_STATUS, false);
    }

    public static void cacheRestStatus(Context context,boolean isrested){
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
        e.putBoolean(KEY_REST_STATUS, isrested);
        e.commit();
    }

}
