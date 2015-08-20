package com.diting.zgy.forworkers.atys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.diting.zgy.forworkers.Config;
import com.diting.zgy.forworkers.R;
import com.diting.zgy.forworkers.adapter.RvAdapterCurrent;
import com.diting.zgy.forworkers.adapter.data.DataOrderCurrent;
import com.diting.zgy.forworkers.adapter.data.DataRepairItems;
import com.diting.zgy.forworkers.net.GetOrderCurrent;
import com.diting.zgy.forworkers.net.GetRepairItems;

import java.util.List;

/**
 * Created by Administrator on 2015/8/6.
 */
public class FraOrderCurrent extends Fragment
{
    private RecyclerView rv;
    private RvAdapterCurrent adapter=null;
    private String account_num,token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        initData();

        View rootView = inflater.inflate(R.layout.tab_order_current,container,false);
        rv = (RecyclerView) rootView.findViewById(R.id.id_recyclerview_order_current);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new RvAdapterCurrent();        //创建adapter
        rv.setAdapter(adapter);                  //绑定adapter与recyclerview

        loadData();

        adapter.setOnRecyclerViewItemClickListenerWork(new RvAdapterCurrent.OnRecyclerViewItemClickListenerWork() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(getActivity(), data + "点击了", Toast.LENGTH_LONG).show();
                launchOrderCurrent(adapter.getOrderNum(Integer.parseInt(data)));
            }
        });

        adapter.setOnRecyclerViewItemClickListenerAbnormal(new RvAdapterCurrent.OnRecyclerViewItemClickListenerAbnormal() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(getActivity(), data + "点击了", Toast.LENGTH_LONG).show();
                SubmitOrderAbnormal(adapter.getOrderNum(Integer.parseInt(data)));
            }

        });

        return rootView;
    }

    private void initData() {
        account_num = getActivity().getIntent().getStringExtra(Config.KEY_ACCOUNT_NUM);
        token = getActivity().getIntent().getStringExtra(Config.KEY_TOKEN);
    }

    private void loadData() {
        new GetOrderCurrent(account_num,token,new GetOrderCurrent.SuccessCallback(){
            @Override
            public void onSuccess(List<DataOrderCurrent> OrderCurrentItems) {
                adapter.clear();
                adapter.addAll(OrderCurrentItems);
            }
        },new GetOrderCurrent.FailCallback(){

            @Override
            public void onFail(int errorCode) {
                if (errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
                    startActivity(new Intent(getActivity(),AtyLogin.class));
                    getActivity().finish();
                }else{
                    Toast.makeText(getActivity(),"Fail to load orders,please try later",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void launchOrderCurrent(String order_num) {
        new GetRepairItems(order_num,token,new GetRepairItems.SuccessCallback(){

            @Override
            public void onSuccess(List<DataRepairItems> RepairItems) {
                Toast.makeText(getActivity(),"进入订单",Toast.LENGTH_LONG).show();

                //传入信息并进入订单页面
            }
        },new GetRepairItems.FailCallback(){

            @Override
            public void onFail(int errorCode) {
                if (errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
                    startActivity(new Intent(getActivity(),AtyLogin.class));
                    getActivity().finish();
                }else{
                    Toast.makeText(getActivity(),"Fail to get orders,please try later",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void SubmitOrderAbnormal(String orderNum) {
        //进入异常提交界面
    }

}