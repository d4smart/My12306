package com.d4smart.my12306.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Line {
    private Integer id;

    private String name;

    private String sectionIds;

    private String stationNames;

    @DateTimeFormat(pattern = "HH:mm")
    private Date beginTime;

    @DateTimeFormat(pattern = "HH:mm")
    private Date endTime;

    private BigDecimal price;

    private Integer mileage;

    private String stayTimes;

    private Integer spendTime;

    private Date createTime;

    private Date updateTime;

    public Line(Integer id, String name, String sectionIds, String stationNames, Date beginTime, Date endTime, BigDecimal price, Integer mileage, String stayTimes, Integer spendTime, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.sectionIds = sectionIds;
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

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sectionIds='" + sectionIds + '\'' +
                ", stationNames='" + stationNames + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", price=" + price +
                ", mileage=" + mileage +
                ", stayTimes='" + stayTimes + '\'' +
                ", spendTime=" + spendTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
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

    public String getSectionIds() {
        return sectionIds;
    }

    public void setSectionIds(String sectionIds) {
        this.sectionIds = sectionIds;
    }

    public String getStationNames() {
        return stationNames;
    }

    public void setStationNames(String stationNames) {
        this.stationNames = stationNames == null ? null : stationNames.trim();
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