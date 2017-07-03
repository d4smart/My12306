package com.d4smart.my12306.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Train {
    private Integer id;

    private String code;

    private Integer lineId;

    private String beginStation;

    private String endStation;

    @DateTimeFormat(pattern = "HH:mm")
    private Date beginTime;

    @DateTimeFormat(pattern = "HH:mm")
    private Date endTime;

    private Integer spendTime;

    private Integer mileage;

    private Integer noseatCount;

    private String vehicleType;

    private String trainType;

    private Date createTime;

    private Date updateTime;

    public Train(Integer id, String code, Integer lineId, String beginStation, String endStation, Date beginTime, Date endTime, Integer spendTime, Integer mileage, Integer noseatCount, String vehicleType, String trainType, Date createTime, Date updateTime) {
        this.id = id;
        this.code = code;
        this.lineId = lineId;
        this.beginStation = beginStation;
        this.endStation = endStation;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.spendTime = spendTime;
        this.mileage = mileage;
        this.noseatCount = noseatCount;
        this.vehicleType = vehicleType;
        this.trainType = trainType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Train() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getBeginStation() {
        return beginStation;
    }

    public void setBeginStation(String beginStation) {
        this.beginStation = beginStation == null ? null : beginStation.trim();
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation == null ? null : endStation.trim();
    }

    @JsonFormat(pattern="HH:mm",timezone = "GMT+8")
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @JsonFormat(pattern="HH:mm",timezone = "GMT+8")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Integer spendTime) {
        this.spendTime = spendTime;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getNoseatCount() {
        return noseatCount;
    }

    public void setNoseatCount(Integer noseatCount) {
        this.noseatCount = noseatCount;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType == null ? null : vehicleType.trim();
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType == null ? null : trainType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}