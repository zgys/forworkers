package com.diting.zgy.forworkers.net;

import com.diting.zgy.forworkers.Config;
import com.diting.zgy.forworkers.adapter.data.DataOrderCurrent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/11.
 */
public class GetOrderCurrent {
    public GetOrderCurrent(String account_num, String token,final SuccessCallback successCallback,final FailCallback failCallback){
        new NetConnection(Config.SERVER_URL,HttpMethod.POST,new NetConnection.SuccessCallback(){
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    switch(obj.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if(successCallback!=null){
                                JSONArray OrderCurrentJsonArray = obj.getJSONArray(Config.KEY_ITEMS_ORDER_CURRENT);
                                JSONObject OrderCurrentObject = null;
                                List<DataOrderCurrent> OrderCurrentItems = new ArrayList<DataOrderCurrent>();

                                for(int i = 0;i < OrderCurrentJsonArray.length();i++){
                                    OrderCurrentObject = OrderCurrentJsonArray.getJSONObject(i);
                                    OrderCurrentItems.add(new DataOrderCurrent(OrderCurrentObject.getString(Config.KEY_ORDER_NUM),
                                            OrderCurrentObject.getString(Config.KEY_ORDER_SERVICE_TIME),
                                            OrderCurrentObject.getString(Config.KEY_ORDER_SITE),
                                            OrderCurrentObject.getString(Config.KEY_ORDER_PROJECT),
                                            OrderCurrentObject.getInt(Config.KEY_ORDER_STATUS)));
                                }
                                successCallback.onSuccess(OrderCurrentItems);
                            }
                            break;
                        case Config.RESULT_STATUS_INVALID_TOKEN:
                            if (failCallback!=null){
                                failCallback.onFail(Config.RESULT_STATUS_INVALID_TOKEN);
                            }
                            break;
                        default:
                            if (failCallback!=null){
                                failCallback.onFail(Config.RESULT_STATUS_FAIL);
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (failCallback!=null){
                        failCallback.onFail(Config.RESULT_STATUS_FAIL);
                    }
                }
            }
        },new NetConnection.FailCallback(){

            @Override
            public void onFail() {
                if (failCallback!=null){
                    failCallback.onFail(Config.RESULT_STATUS_FAIL);
                }
            }
            },Config.KEY_ACTION,Config.ACTION_GET_ORDER_CURRENT,
                Config.KEY_ACCOUNT_NUM,account_num,
                Config.KEY_TOKEN,token);
    }

    public static interface SuccessCallback{
        void onSuccess(List<DataOrderCurrent> OrderCurrentItems);
    }

    public static interface FailCallback{
        void onFail(int errorCode);
    }
}
