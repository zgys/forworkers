package com.diting.zgy.forworkers.adapter.data;

/**
 * Created by Administrator on 2015/8/16.
 */
public class DataRepairItems {

    private String repairNum = null;               //维修编号
    private String repairName = null;              //维修名称
    private String repairPrice = null;             //维修价格
    private String repairDescription = null;       //此项维修描述

    public DataRepairItems(String repair_num, String repair_price, String repair_name, String repair_description){
        this.repairNum = repair_num;
        this.repairName = repair_name;
        this.repairPrice = repair_price;
        this.repairDescription = repair_description;
    }

    public void setRepairNum(String repairNum) {
        this.repairNum = repairNum;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }

    public void setRepairPrice(String repairPrice) {
        this.repairPrice = repairPrice;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public String getRepairNum() {

        return repairNum;
    }

    public String getRepairName() {
        return repairName;
    }

    public String getRepairPrice() {
        return repairPrice;
    }

    public String getRepairDescription() {
        return repairDescription;
    }
}
