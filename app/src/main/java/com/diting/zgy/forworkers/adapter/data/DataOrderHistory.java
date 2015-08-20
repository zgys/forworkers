package com.diting.zgy.forworkers.adapter.data;

/**
 * Created by Administrator on 2015/8/8.
 */
public class DataOrderHistory {
    private String orderNum = null;                 //订单号
    private String orderServiceTime = null;        //服务时间
    private String orderSite = null;                //服务地点
    private String orderProject = null;             //服务项目

    public DataOrderHistory(String order_num, String order_service_time, String order_site, String order_project) {
        this.orderNum = order_num;
        this.orderServiceTime = order_service_time;
        this.orderSite = order_site;
        this.orderProject = order_project;
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
}
