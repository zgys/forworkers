package com.diting.zgy.forworkers.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.diting.zgy.forworkers.R;
import com.diting.zgy.forworkers.adapter.data.DataOrderCurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/16.
 */
public class RvAdapterCurrent extends RecyclerView.Adapter implements View.OnClickListener{
    private List<DataOrderCurrent> data = new ArrayList<DataOrderCurrent>();
    private OnRecyclerViewItemClickListenerWork mOnItemClickListenerWork = null;
    private OnRecyclerViewItemClickListenerAbnormal mOnItemClickListenerAbnormal = null;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private View root;
        private TextView tvOrderNum,tvOrderServiceTime,tvOrderSite,tvOrderProject;
        private Button btnOrderWork;

        public ViewHolder(View root) {
            super(root);

            tvOrderNum = (TextView) root.findViewById(R.id.tv_order_num);
            tvOrderServiceTime = (TextView) root.findViewById(R.id.tv_order_service_time);
            tvOrderSite = (TextView) root.findViewById(R.id.tv_order_site);
            tvOrderProject = (TextView) root.findViewById(R.id.tv_order_project);
            btnOrderWork = (Button) root.findViewById(R.id.btn_order_work);
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

        public Button getBtnOrderWork() {
            return btnOrderWork;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_order_current, null);
        ViewHolder vh = new ViewHolder(view);
        vh.getBtnOrderWork().setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder vh = (ViewHolder) viewHolder;

        DataOrderCurrent cd = data.get(i);

        vh.getTvOrderNum().setText("单号："+cd.getOrderNum());
        vh.getTvOrderServiceTime().setText("时间："+cd.getOrderServiceTime());
        vh.getTvOrderSite().setText("地点："+cd.getOrderSite());
        vh.getTvOrderProject().setText("项目："+cd.getOrderProject());
        vh.getBtnOrderWork().setTag(i);

        //已订单号为标记保存在itemView的Tag中，以便点击时进行获取
//        vh.itemView.setTag(cd.getOrderNum());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addAll(List<DataOrderCurrent> data){
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
            case R.id.btn_order_work:
                if (mOnItemClickListenerWork != null) {
                    //使用getTag方法获取数据
                    mOnItemClickListenerWork.onItemClick(v, v.getTag()+"");
                }
                break;
        }
    }

    public String getOrderNum(int i) {
        return data.get(i).getOrderNum();
    }

    //点击接口
    public static interface OnRecyclerViewItemClickListenerWork {
        void onItemClick(View view , String data);
    }

    public void setOnRecyclerViewItemClickListenerWork(OnRecyclerViewItemClickListenerWork listener) {
        this.mOnItemClickListenerWork = listener;
    }

    public static interface OnRecyclerViewItemClickListenerAbnormal{
        void onItemClick(View view , String data);
    }

    public void setOnRecyclerViewItemClickListenerAbnormal(OnRecyclerViewItemClickListenerAbnormal listener){
        this.mOnItemClickListenerAbnormal = listener;
    }

}
