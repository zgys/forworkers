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
import com.diting.zgy.forworkers.adapter.RvAdapterTake;
import com.diting.zgy.forworkers.adapter.data.DataOrderTake;
import com.diting.zgy.forworkers.net.GetOrderTake;
import com.diting.zgy.forworkers.net.SubmitOrderTake;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/6.
 */
public class FraOrderTake extends Fragment
{

    private RecyclerView rv;
    private RvAdapterTake adapter=null;
    private String account_num,token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        initData();

        View rootView = inflater.inflate(R.layout.tab_order_take, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.id_recyclerview);
        //为recyclerview创建布局管理器
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //创建adapter
        adapter = new RvAdapterTake();

        loadData();

        //绑定adapter与recyclerview
        rv.setAdapter(adapter);

        adapter.setOnRecyclerViewItemClickListenerTake(new RvAdapterTake.OnRecyclerViewItemClickListenerTake() {
            @Override
            public void onItemClick(View view, String data) {
                //Toast.makeText(getActivity(),data, Toast.LENGTH_LONG).show();
                submitData(adapter.getOrderNum(Integer.parseInt(data)));
                loadData();
            }
        });

        return rootView;
    }

    private void initData() {
        account_num = getActivity().getIntent().getStringExtra(Config.KEY_ACCOUNT_NUM);
        token = getActivity().getIntent().getStringExtra(Config.KEY_TOKEN);
    }

    private void loadData() {
        new GetOrderTake(account_num,token,new GetOrderTake.SuccessCallback(){
            @Override
            public void onSuccess(List<DataOrderTake> OrderTakeItems) {
                adapter.clear();
                adapter.addAll(OrderTakeItems);
            }
        },new GetOrderTake.FailCallback(){

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

    private void submitData(String order_num) {
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
}