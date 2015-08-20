package com.diting.zgy.forworkers.atys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.diting.zgy.forworkers.Config;
import com.diting.zgy.forworkers.R;
import com.diting.zgy.forworkers.net.Login;

public class AtyLogin extends Activity {

    private EditText etAccount=null;
    private EditText etPasswd=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_aty_login);


        etAccount = (EditText)findViewById(R.id.etAccountNum);
        etPasswd = (EditText)findViewById(R.id.etPasswd);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etAccount.getText())){
                    Toast.makeText(AtyLogin.this, "账号不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(etPasswd.getText())){
                    Toast.makeText(AtyLogin.this, "密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }

                final ProgressDialog pd = ProgressDialog.show(AtyLogin.this, "正在连接", "连接到Server，请等待");

                new Login(etAccount.getText().toString(),etPasswd.getText().toString(),new Login.SuccessCallback(){

                    @Override
                    public void onSuccess(String token,boolean isrested){

                        pd.dismiss();

                        Config.cacheToken(AtyLogin.this, token);
                        Config.cacheAccountNum(AtyLogin.this, etAccount.getText().toString());
                        Config.cacheRestStatus(AtyLogin.this,isrested);

                        Intent i = new Intent(AtyLogin.this,AtyMain.class);
                        i.putExtra(Config.KEY_TOKEN,token);
                        i.putExtra(Config.KEY_ACCOUNT_NUM,etAccount.getText().toString());
                        i.putExtra(Config.KEY_REST_STATUS,isrested);
                        startActivity(i);

                        finish();
                    }
                },new Login.FailCallback(){

                    @Override
                    public void onFail(){
                        pd.dismiss();

                        Toast.makeText(AtyLogin.this, "登录失败", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

}
