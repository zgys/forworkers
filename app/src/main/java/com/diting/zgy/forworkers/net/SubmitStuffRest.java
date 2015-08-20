package com.diting.zgy.forworkers.net;

import com.diting.zgy.forworkers.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/8/15.
 */
public class SubmitStuffRest {
    public SubmitStuffRest(String account_num,String token,final SuccessCallback successCallback,final FailCallback failCallback){
        new NetConnection(Config.SERVER_URL,HttpMethod.POST,new NetConnection.SuccessCallback(){

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);

                    switch(obj.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallback!=null){
                                successCallback.onSuccess();
                            }
                            break;
                        case Config.RESULT_STATUS_INVALID_TOKEN:
                            if(failCallback!=null){
                                failCallback.onFail(Config.RESULT_STATUS_INVALID_TOKEN);
                            }
                            break;
                        case Config.RESULT_STATUS_FAIL:
                            if(failCallback!=null){
                                failCallback.onFail(Config.RESULT_STATUS_FAIL);
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if(failCallback!=null){
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
        },Config.KEY_ACTION,Config.ACTION_REST_REQUEST,
                Config.KEY_ACCOUNT_NUM,account_num,
                Config.KEY_TOKEN,token);
    }

    public static interface SuccessCallback{
        void onSuccess();
    }

    public static interface FailCallback{
        void onFail(int errorCode);
    }
}
