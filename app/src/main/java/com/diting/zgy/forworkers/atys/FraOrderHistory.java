package com.diting.zgy.forworkers.atys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.diting.zgy.forworkers.Config;
import com.diting.zgy.forworkers.R;
import com.diting.zgy.forworkers.adapter.RvAdapterHistory;
import com.diting.zgy.forworkers.adapter.RvAdapterTake;
import com.diting.zgy.forworkers.adapter.data.DataOrderHistory;
import com.diting.zgy.forworkers.events.EventStuffRestOrWork;
import com.diting.zgy.forworkers.net.GetOrderTake;
import com.diting.zgy.forworkers.net.SubmitOrderIgnore;
import com.diting.zgy.forworkers.net.SubmitOrderTake;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/6.
 */
public class FraOrderHistory extends Fragment
{

    private RecyclerView rv;
    private RvAdapterHistory adapter=null;
    private String account_num,token;
    private TextView tvForRest = null;
    private FrameLayout layout = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        initData();

        View rootView = inflater.inflate(R.layout.tab_order_take, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.id_recyclerview_order_take);        //为recyclerview创建布局管理器
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        layout = (FrameLayout)rootView.findViewById(R.id.id_fra_order_take);
        adapter = new RvAdapterHistory();        //创建adapter
        rv.setAdapter(adapter);        //绑定adapter与recyclerview

            loadData();

        adapter.setOnRecyclerViewItemClickListenerDetails(new RvAdapterHistory.OnRecyclerViewItemClickListenerDetails() {
            @Override
            public void onItemClick(View view, String data) {
                //Toast.makeText(getActivity(),data, Toast.LENGTH_LONG).show();
                submitOrderTake(adapter.getOrderNum(Integer.parseInt(data)));
            }
        });

        return rootView;
    }

    private void initData() {
        account_num = getActivity().getIntent().getStringExtra(Config.KEY_ACCOUNT_NUM);
        token = getActivity().getIntent().getStringExtra(Config.KEY_TOKEN);
    }

    private void loadData() {
        List<DataOrderHistory> OrderCurrentItems = new ArrayList<DataOrderHistory>();
        OrderCurrentItems.add(new DataOrderHistory("few","fewfe","fe","fe"));
        adapter.clear();
        adapter.addAll(OrderCurrentItems);
    }

    private void submitOrderTake(String order_num) {
        new SubmitOrderTake(account_num,token,order_num,new SubmitOrderTake.SuccessCallback(){

            @Override
            public void onSuccess() {
                Toast.makeText(getActivity(),"抢单成功",Toast.LENGTH_LONG).show();
                loadData();
            }
        },new SubmitOrderTake.FailCallback(){

            @Override
            public void onFail(int errorCode) {
                if (errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
                    startActivity(new Intent(getActivity(),AtyLogin.class));
                    getActivity().finish();
                }else{
                    Toast.makeText(getActivity(),"Fail to take orders,please try later",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void submitOrderIgnore(String order_num) {
        new SubmitOrderIgnore(account_num,token,order_num,new SubmitOrderIgnore.SuccessCallback(){

            @Override
            public void onSuccess() {
                Toast.makeText(getActivity(),"放弃订单成功",Toast.LENGTH_LONG).show();
                loadData();
            }
        },new SubmitOrderIgnore.FailCallback(){

            @Override
            public void onFail(int errorCode) {
                if (errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
                    startActivity(new Intent(getActivity(),AtyLogin.class));
                    getActivity().finish();
                }else{
                    Toast.makeText(getActivity(),"Fail to ignore orders,please try later",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void showTvForRested(){
        tvForRest = new TextView(getActivity());
        tvForRest.setTextColor(Color.BLUE);
        tvForRest.setTextSize(20);
        tvForRest.setText("休息中。。。。。\n" + "不接收新订单\n");
        tvForRest.setGravity(Gravity.CENTER);
        tvForRest.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT));
        tvForRest.setBackgroundColor(Color.parseColor("#86222222"));
        layout.addView(tvForRest);
    }

    private void removeTvForRested(){
        layout.removeView(tvForRest);
    }

    public void onEventMainThread(EventStuffRestOrWork event){
        switch(event.getStuffStatusCode()){
            case Config.STUFF_REST_REQUEST:
                adapter.clear();
                showTvForRested();
                break;
            case Config.STUFF_WORK_REQUEST:
                loadData();
                removeTvForRested();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        removeTvForRested();
    }
}