package com.diting.zgy.forworkers.net;

import com.diting.zgy.forworkers.Config;
import com.diting.zgy.forworkers.adapter.data.DataRepairItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 */
public class GetRepairItems {
    public GetRepairItems(String orderNum, String token,final SuccessCallback successCallback,final FailCallback failCallback){
        new NetConnection(Config.SERVER_URL,HttpMethod.POST,new NetConnection.SuccessCallback(){
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    switch(obj.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if(successCallback!=null){
                                JSONArray RepairItemsJsonArray = obj.getJSONArray(Config.KEY_ITEMS_ORDER_TAKE);
                                JSONObject RepairItemsObject = null;
                                List<DataRepairItems> RepairItems = new ArrayList<DataRepairItems>();

                                for(int i = 0;i < RepairItemsJsonArray.length();i++){
                                    RepairItemsObject = RepairItemsJsonArray.getJSONObject(i);
                                    RepairItems.add(new DataRepairItems(RepairItemsObject.getString(Config.KEY_REPAIR_NUM),
                                            RepairItemsObject.getString(Config.KEY_ORDER_SERVICE_TIME),
                                            RepairItemsObject.getString(Config.KEY_ORDER_SITE),
                                            RepairItemsObject.getString(Config.KEY_ORDER_PROJECT)));
                                }
                                successCallback.onSuccess(RepairItems);
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
        },Config.KEY_ACTION,Config.ACTION_GET_REPAIR_ITEMS,
                Config.KEY_ORDER_NUM,orderNum,
                Config.KEY_TOKEN,token);
    }

    public static interface SuccessCallback{
        void onSuccess(List<DataRepairItems> RepairItems);
    }

    public static interface FailCallback{
        void onFail(int errorCode);
    }
}
