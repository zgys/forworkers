package com.diting.zgy.forworkers.adapter.data;

/**
 * Created by Administrator on 2015/8/16.
 */
public class DataOrderCurrent {
    private String orderNum = null;                 //订单号
    private String orderServiceTime = null;        //服务时间
    private String orderSite = null;                //服务地点
    private String orderProject = null;             //服务项目
    private int orderStatus;              //订单状态

    public DataOrderCurrent(String orderNum, String orderServiceTime, String orderSite, String orderProject, int orderStatus) {
        this.orderNum = orderNum;
        this.orderServiceTime = orderServiceTime;
        this.orderSite = orderSite;
        this.orderProject = orderProject;
        this.orderStatus = orderStatus;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public void setOrderServiceTime(String orderServiceTime) {
        this.orderServiceTime = orderServiceTime;
    }

    public void setOrderSite(String orderSite) {
        this.orderSite = orderSite;
    }

    public void setOrderProject(String orderProject) {
        this.orderProject = orderProject;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderNum() {

        return orderNum;
    }

    public String getOrderServiceTime() {
        return orderServiceTime;
    }

    public String getOrderSite() {
        return orderSite;
    }

    public String getOrderProject() {
        return orderProject;
    }

    public int getOrderStatus() {
        return orderStatus;
    }
}
