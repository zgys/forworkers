package com.diting.zgy.forworkers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.diting.zgy.forworkers.atys.AtyLogin;
import com.diting.zgy.forworkers.atys.AtyMain;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String token = Config.getCachedToken(this);
        String account_num = Config.getCachedAccountNum(this);

        if (token!=null&&account_num!=null) {
            Intent i = new Intent(this, AtyMain.class);
            i.putExtra(Config.KEY_TOKEN,token);
            i.putExtra(Config.KEY_ACCOUNT_NUM,account_num);
            startActivity(i);
        }else{
            startActivity(new Intent(this, AtyLogin.class));
        }
        finish();
    }

}
