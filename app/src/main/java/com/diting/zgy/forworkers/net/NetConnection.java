package com.diting.zgy.forworkers.net;

import android.os.AsyncTask;

import com.diting.zgy.forworkers.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2015/7/31.
 */
public class NetConnection {
    public NetConnection(final String url, final HttpMethod method, final SuccessCallback successCallback,final FailCallback failCallback, final String ... kvs){

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {

                StringBuffer paramsStr = new StringBuffer();
                for(int i = 0; i< kvs.length; i+=2){
                    paramsStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
                }
                try {
                    URLConnection uc;
                    switch(method){
                        case POST:
                            uc = new URL(url).openConnection();
                            uc.setDoOutput(true);
                            uc.setConnectTimeout(3000);
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
                            bw.write(paramsStr.toString());
                            bw.flush();
                            break;
                        default:
                            uc = new URL(url+"?"+paramsStr.toString()).openConnection();
                            break;
                    }

                    System.out.println("Request url:" + uc.getURL());
                    System.out.println("Request data"+paramsStr);

                    BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(),Config.CHARSET));
                    String line = null;
                    StringBuffer result = new StringBuffer();
                    while((line=br.readLine())!=null){
                        result.append(line);
                    }
                    System.out.println("Result:"+result);
                    return result.toString();
                } catch (SocketTimeoutException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            //doInBackground方法的返回值作为onPostExecute方法的传入参数
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if(result!=null) {
                    if (successCallback != null) {
                        successCallback.onSuccess(result);
                    }
                }else{
                    if (failCallback!=null){
                        failCallback.onFail();
                    }
                }
            }
        }.execute();
    }

    public static interface SuccessCallback{
        void onSuccess(String result);
    }

    public static interface FailCallback{
        void onFail();
    }
}
