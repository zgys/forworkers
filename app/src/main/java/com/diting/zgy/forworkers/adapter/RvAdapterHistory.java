package com.diting.zgy.forworkers.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.diting.zgy.forworkers.R;
import com.diting.zgy.forworkers.adapter.data.DataOrderHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/8.
 */
public class RvAdapterHistory extends RecyclerView.Adapter implements View.OnClickListener{

    private List<DataOrderHistory> data = new ArrayList<DataOrderHistory>();
    private OnRecyclerViewItemClickListenerDetails mOnItemClickListenerDetails = null;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private View root;
        private TextView tvOrderNum,tvOrderServiceTime,tvOrderSite,tvOrderProject;
        private Button btnOrderDetails;

        public ViewHolder(View root) {
            super(root);

            tvOrderNum = (TextView) root.findViewById(R.id.tv_order_num);
            tvOrderServiceTime = (TextView) root.findViewById(R.id.tv_order_service_time);
            tvOrderSite = (TextView) root.findViewById(R.id.tv_order_site);
            tvOrderProject = (TextView) root.findViewById(R.id.tv_order_project);
            btnOrderDetails = (Button) root.findViewById(R.id.btn_order_details);
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

        public Button getBtnOrderDetails() {
            return btnOrderDetails;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_order_history, null);
        ViewHolder vh = new ViewHolder(view);
        vh.getBtnOrderDetails().setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder vh = (ViewHolder) viewHolder;

        DataOrderHistory cd = data.get(i);

        vh.getTvOrderNum().setText("单号："+cd.getOrderNum());
        vh.getTvOrderServiceTime().setText("时间："+cd.getOrderServiceTime());
        vh.getTvOrderSite().setText("地点："+cd.getOrderSite());
        vh.getTvOrderProject().setText("项目："+cd.getOrderProject());
        vh.getBtnOrderDetails().setTag(i);

        //已订单号为标记保存在itemView的Tag中，以便点击时进行获取
//        vh.itemView.setTag(cd.getOrderNum());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addAll(List<DataOrderHistory> data){
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
            case R.id.btn_order_details:
                if (mOnItemClickListenerDetails != null) {
                    //使用getTag方法获取数据
                    mOnItemClickListenerDetails.onItemClick(v, v.getTag()+"");
                }
                break;
        }
    }

    public String getOrderNum(int i) {
        return data.get(i).getOrderNum();
    }

    //详情点击接口
    public static interface OnRecyclerViewItemClickListenerDetails {
        void onItemClick(View view , String data);
    }

    public void setOnRecyclerViewItemClickListenerDetails(OnRecyclerViewItemClickListenerDetails listener) {
        this.mOnItemClickListenerDetails = listener;
    }

}
