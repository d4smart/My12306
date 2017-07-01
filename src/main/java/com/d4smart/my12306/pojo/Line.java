package com.d4smart.my12306.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Line {
    private Integer id;

    private String name;

    private String stationIds;

    private String stationNames;

    private Date beginTime;

    private Date endTime;

    private BigDecimal price;

    private Integer mileage;

    private String stayTimes;

    private Integer spendTime;

    private Date createTime;

    private Date updateTime;

    public Line(Integer id, String name, String stationIds, String stationNames, Date beginTime, Date endTime, BigDecimal price, Integer mileage, String stayTimes, Integer spendTime, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.stationIds = stationIds;
        this.stationNames = stationNames;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.price = price;
        this.mileage = mileage;
        this.stayTimes = stayTimes;
        this.spendTime = spendTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Line() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStationIds() {
        return stationIds;
    }

    public void setStationIds(String stationIds) {
        this.stationIds = stationIds == null ? null : stationIds.trim();
    }

    public String getStationNames() {
        return stationNames;
    }

    public void setStationNames(String stationNames) {
        this.stationNames = stationNames == null ? null : stationNames.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getStayTimes() {
        return stayTimes;
    }

    public void setStayTimes(String stayTimes) {
        this.stayTimes = stayTimes == null ? null : stayTimes.trim();
    }

    public Integer getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Integer spendTime) {
        this.spendTime = spendTime;
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