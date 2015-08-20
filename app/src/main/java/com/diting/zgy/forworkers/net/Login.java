package com.diting.zgy.forworkers.net;

import com.diting.zgy.forworkers.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/8/2.
 */
public class Login {
    public Login(String account, String passwd, final SuccessCallback successCallback, final FailCallback failCallback){
        new NetConnection(Config.SERVER_URL,HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);

                    switch (obj.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallback!=null){
                                successCallback.onSuccess(obj.getString(Config.KEY_TOKEN),obj.getBoolean(Config.KEY_REST_STATUS));
                            }
                            break;
                        default:
                            if(failCallback!=null){
                                failCallback.onFail();
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if(failCallback!=null){
                        failCallback.onFail();
                    }
                }

            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if(failCallback!=null){
                    failCallback.onFail();
                }
            }
        },Config.KEY_ACTION,Config.ACTION_LOGIN,Config.KEY_ACCOUNT_NUM,account,Config.KEY_PASSWD,passwd);
    }

    public static interface SuccessCallback{
        void onSuccess(String token,boolean isrested);
    }

    public static interface FailCallback{
        void onFail();
    }
}
