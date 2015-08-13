package com.diting.zgy.forworkers.net;

import com.diting.zgy.forworkers.Config;
import com.diting.zgy.forworkers.adapter.data.DataOrderTake;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/11.
 */
public class GetOrderTake {
    public GetOrderTake(String account_num, String token,final SuccessCallback successCallback,final FailCallback failCallback){
        new NetConnection(Config.SERVER_URL,HttpMethod.POST,new NetConnection.SuccessCallback(){
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    switch(obj.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if(successCallback!=null){
                                JSONArray OrderTakeJsonArray = obj.getJSONArray(Config.KEY_ITEMS_ORDER_TAKE);
                                JSONObject OrderTakeObject = null;
                                List<DataOrderTake> OrderTakeItems = new ArrayList<DataOrderTake>();

                                for(int i = 0;i < OrderTakeJsonArray.length();i++){
                                    OrderTakeObject = OrderTakeJsonArray.getJSONObject(i);
                                    OrderTakeItems.add(new DataOrderTake(OrderTakeObject.getString(Config.KEY_ORDER_NUM),
                                            OrderTakeObject.getString(Config.KEY_ORDER_SERVICE_TIME),
                                            OrderTakeObject.getString(Config.KEY_ORDER_SITE),
                                            OrderTakeObject.getString(Config.KEY_ORDER_PROJECT)));
                                }
                                successCallback.onSuccess(OrderTakeItems);
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
        },Config.KEY_ACTION,Config.ACTION_GET_ORDER_TAKE,
                Config.KEY_ACCOUNT_NUM,account_num,
                Config.KEY_TOKEN,token);
    }

    public static interface SuccessCallback{
        void onSuccess(List<DataOrderTake> OrderTakeItems);
    }

    public static interface FailCallback{
        void onFail(int errorCode);
    }
}
