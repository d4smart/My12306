package com.d4smart.my12306.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Section {
    private Integer id;

    private String from;

    private String to;

    private Integer mileage;

    private BigDecimal price;

    private Date createTime;

    private Date updateTime;

    public Section(Integer id, String from, String to, Integer mileage, BigDecimal price, Date createTime, Date updateTime) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.mileage = mileage;
        this.price = price;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Section() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from == null ? null : from.trim();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to == null ? null : to.trim();
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