package com.diting.zgy.forworkers.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.diting.zgy.forworkers.R;
import com.diting.zgy.forworkers.adapter.data.DataOrderTake;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/8.
 */
public class RvAdapterTake extends RecyclerView.Adapter implements View.OnClickListener{

    private List<DataOrderTake> data = new ArrayList<DataOrderTake>();
    private OnRecyclerViewItemClickListenerTake mOnItemClickListenerTake = null;
    private OnRecyclerViewItemClickListenerIgnore mOnItemClickListenerIgnore = null;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private View root;
        private TextView tvOrderNum,tvOrderServiceTime,tvOrderSite,tvOrderProject;
        private Button btnOrderTake, btnOrderIgnore;

        public ViewHolder(View root) {
            super(root);

            tvOrderNum = (TextView) root.findViewById(R.id.tv_order_num);
            tvOrderServiceTime = (TextView) root.findViewById(R.id.tv_order_service_time);
            tvOrderSite = (TextView) root.findViewById(R.id.tv_order_site);
            tvOrderProject = (TextView) root.findViewById(R.id.tv_order_project);
            btnOrderTake = (Button) root.findViewById(R.id.btn_order_take);
            btnOrderIgnore = (Button) root.findViewById(R.id.btn_order_ignore);
        }

        public TextView getTvOrderNum() {
            return tvOrderNum;
        }

        public TextView getTvOrderServiceTime() {
            return tvOrderServiceTime;
        }

        public TextView getTvOrderSite() {
            return tvOrderSite;
        }

        public TextView getTvOrderProject() {
            return tvOrderProject;
        }

        public Button getBtnOrderTake() {
            return btnOrderTake;
        }

        public Button getBtnOrderIgnore() {
            return btnOrderIgnore;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_order_take, null);
        ViewHolder vh = new ViewHolder(view);
        vh.getBtnOrderTake().setOnClickListener(this);
        vh.getBtnOrderIgnore().setOnClickListener(this);
        return vh;
    }

    public String getOrderNum(int i) {
        return data.get(i).getOrderNum();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder vh = (ViewHolder) viewHolder;

        DataOrderTake cd = data.get(i);

        vh.getTvOrderNum().setText("单号："+cd.getOrderNum());
        vh.getTvOrderServiceTime().setText("时间："+cd.getOrderServiceTime());
        vh.getTvOrderSite().setText("地点："+cd.getOrderSite());
        vh.getTvOrderProject().setText("项目："+cd.getOrderProject());
        vh.getBtnOrderTake().setTag(i);
        vh.getBtnOrderIgnore().setTag(i);

        //已订单号为标记保存在itemView的Tag中，以便点击时进行获取
//        vh.itemView.setTag(cd.getOrderNum());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addAll(List<DataOrderTake> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_order_take:
                if (mOnItemClickListenerTake != null) {
                    //使用getTag方法获取数据
                    mOnItemClickListenerTake.onItemClick(v, v.getTag()+"");
                }
                break;
            case R.id.btn_order_ignore:
                if (mOnItemClickListenerIgnore != null) {
                    //使用getTag方法获取数据
                    mOnItemClickListenerIgnore.onItemClick(v, v.getTag()+"");
                }
                break;
        }
    }

    //接单点击接口
    public static interface OnRecyclerViewItemClickListenerTake {
        void onItemClick(View view , String data);
    }

    //忽略点击接口
    public static interface OnRecyclerViewItemClickListenerIgnore {
        void onItemClick(View view , String data);
    }

    public void setOnRecyclerViewItemClickListenerTake(OnRecyclerViewItemClickListenerTake listener) {
        this.mOnItemClickListenerTake = listener;
    }

    public void setOnRecyclerViewItemClickListenerIgnore(OnRecyclerViewItemClickListenerIgnore listener) {
        this.mOnItemClickListenerIgnore = listener;
    }

}
