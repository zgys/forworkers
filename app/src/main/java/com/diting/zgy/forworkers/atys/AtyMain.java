package com.diting.zgy.forworkers.atys;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.diting.zgy.forworkers.Config;
import com.diting.zgy.forworkers.R;
import com.diting.zgy.forworkers.adapter.TabAdapter;
import com.diting.zgy.forworkers.events.EventStuffRestOrWork;
import com.diting.zgy.forworkers.net.SubmitStuffRest;
import com.diting.zgy.forworkers.net.SubmitStuffWork;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class AtyMain extends FragmentActivity
{
    private ViewPager mViewPager;
    private TabPageIndicator mTabPageIndicator;
    private TabAdapter mAdapter ;
    private List<Fragment> mFragments;
    private String account_num,token;
    private boolean isRested = false;
    private TextView tvStuffRestOrWork = null;
    ToggleButton mTogBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_aty_main);
        initData();
        initView();
        initAction();

    }

    private void initData() {
        account_num = getIntent().getStringExtra(Config.KEY_ACCOUNT_NUM);
        token = getIntent().getStringExtra(Config.KEY_TOKEN);
        isRested = getIntent().getBooleanExtra(Config.KEY_REST_STATUS,false);
    }

    private void initView()
    {
        //content
        mFragments = new ArrayList<Fragment>();
        Fragment mTab01 = new FraOrderTake();
        Fragment mTab02 = new FraOrderCurrent();
        Fragment mTab03 = new FraOrderHistory();
        mFragments.add(mTab01);
        mFragments.add(mTab02);
        mFragments.add(mTab03);

        tvStuffRestOrWork = (TextView) findViewById(R.id.tv_stuff_rest_or_work);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mTabPageIndicator = (TabPageIndicator) findViewById(R.id.id_indicator);
        mAdapter = new TabAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mAdapter);

        mTabPageIndicator.setViewPager(mViewPager, 0);
        mTabPageIndicator.setCurrentItem(0);

        //bottom
        mTogBtn = (ToggleButton) findViewById(R.id.mTogBtn);

        //初始化bottom
        if (isRested){
            mTogBtn.setChecked(true);
            tvStuffRestOrWork.setText("休息中");
        }else{
            mTogBtn.setChecked(false);
            tvStuffRestOrWork.setText("可接受新的订单");
        }
    }

    private void initAction() {
        mTogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTogBtn.isChecked()){
                    //选中
                    //Toast.makeText(AtyMain.this, "选中", Toast.LENGTH_LONG).show();
                    //向服务器发送休息消息
                    new SubmitStuffRest(account_num,token,new SubmitStuffRest.SuccessCallback() {
                        @Override
                        public void onSuccess() {
                            //通知FraOrderTake
                            EventBus.getDefault().post(new EventStuffRestOrWork(Config.STUFF_REST_REQUEST));
                            Config.cacheRestStatus(getBaseContext(), true);
                            tvStuffRestOrWork.setText("休息中");
                        }
                    }, new SubmitStuffRest.FailCallback() {
                        @Override
                        public void onFail(int errorCode) {
                            if (errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
                                startActivity(new Intent(AtyMain.this,AtyLogin.class));
                                finish();
                            }else{
                                mTogBtn.setChecked(false);
                                Toast.makeText(AtyMain.this,"Fail to post rest request,please try later",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    //未选中
                    //Toast.makeText(AtyMain.this,"未选中",Toast.LENGTH_LONG).show();
                    //向服务器发送工作消息
                    new SubmitStuffWork(account_num,token,new SubmitStuffWork.SuccessCallback() {
                        @Override
                        public void onSuccess() {
                            //通知FraOrderTake
                            EventBus.getDefault().post(new EventStuffRestOrWork(Config.STUFF_WORK_REQUEST));
                            Config.cacheRestStatus(getBaseContext(),false);
                            tvStuffRestOrWork.setText("可接受新的订单");
                        }
                    }, new SubmitStuffWork.FailCallback() {
                        @Override
                        public void onFail(int errorCode) {
                            if (errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
                                startActivity(new Intent(AtyMain.this,AtyLogin.class));
                                finish();
                            }else{
                                mTogBtn.setChecked(true);
                                Toast.makeText(AtyMain.this,"Fail to post work request,please try later",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
//        mTogBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//                if(isChecked){
//                    //选中
//                    //Toast.makeText(AtyMain.this, "选中", Toast.LENGTH_LONG).show();
//                    //向服务器发送休息消息
//                    new SubmitStuffRest(account_num,token,new SubmitStuffRest.SuccessCallback() {
//                        @Override
//                        public void onSuccess() {
//                            //通知FraOrderTake
//                            EventBus.getDefault().post(new EventStuffRestOrWork(Config.STUFF_REST_REQUEST));
//                            Config.cacheRestStatus(getBaseContext(), true);
//                        }
//                    }, new SubmitStuffRest.FailCallback() {
//                        @Override
//                        public void onFail(int errorCode) {
//                            if (errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
//                                startActivity(new Intent(AtyMain.this,AtyLogin.class));
//                                finish();
//                            }else{
//                                //mTogBtn.setChecked(false);
//                                Toast.makeText(AtyMain.this,"Fail to post rest request,please try later",Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
//                }else{
//                    //未选中
//                    //Toast.makeText(AtyMain.this,"未选中",Toast.LENGTH_LONG).show();
//                    //向服务器发送工作消息
//                    new SubmitStuffWork(account_num,token,new SubmitStuffWork.SuccessCallback() {
//                        @Override
//                        public void onSuccess() {
//                            //通知FraOrderTake
//                            EventBus.getDefault().post(new EventStuffRestOrWork(Config.STUFF_WORK_REQUEST));
//                            Config.cacheRestStatus(getBaseContext(),false);
//                        }
//                    }, new SubmitStuffWork.FailCallback() {
//                        @Override
//                        public void onFail(int errorCode) {
//                            if (errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
//                                startActivity(new Intent(AtyMain.this,AtyLogin.class));
//                                finish();
//                            }else{
//                                //mTogBtn.setChecked(true);
//                                Toast.makeText(AtyMain.this,"Fail to post work request,please try later",Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
//                }
//            }
//        });// 添加监听事件
    }

}
