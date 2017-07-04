package com.d4smart.my12306.vo;

import com.d4smart.my12306.pojo.Train;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by d4smart on 2017/7/4 11:27
 */
public class TrainDetailVo {

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

    private Map<String, Integer> tickets;

    public TrainDetailVo() {

    }

    public TrainDetailVo(Train train) {
        this.id = train.getId();
        this.code = train.getCode();
        this.lineId = train.getLineId();
        this.beginStation = train.getBeginStation();
        this.endStation = train.getEndStation();
        this.beginTime = train.getBeginTime();
        this.endTime = train.getEndTime();
        this.spendTime = train.getSpendTime();
        this.mileage = train.getMileage();
        this.noseatCount = train.getNoseatCount();
        this.vehicleType = train.getVehicleType();
        this.trainType = train.getTrainType();
        this.createTime = train.getCreateTime();
        this.updateTime = train.getUpdateTime();
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
        this.code = code;
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
        this.beginStation = beginStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
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
        this.vehicleType = vehicleType;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
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

    public Map<String, Integer> getTickets() {
        return tickets;
    }

    public void setTickets(Map<String, Integer> tickets) {
        this.tickets = tickets;
    }
}
