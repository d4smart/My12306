package com.d4smart.my12306.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Section {
    private Integer id;

    private String fromStation;

    private String toStation;

    private Integer mileage;

    private BigDecimal price;

    private Date createTime;

    private Date updateTime;

    public Section(Integer id, String fromStation, String toStation, Integer mileage, BigDecimal price, Date createTime, Date updateTime) {
        this.id = id;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.mileage = mileage;
        this.price = price;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Section() {
        super();
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", fromStation='" + fromStation + '\'' +
                ", toStation='" + toStation + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
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

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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