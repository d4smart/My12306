package com.d4smart.my12306.pojo;

import java.util.Date;

public class Group {
    private Integer id;

    private String code;

    private String cabin;

    private String seatType;

    private Integer count;

    private String type;

    private Date createTime;

    private Date updateTime;

    public Group(Integer id, String code, String cabin, String seatType, Integer count, String type, Date createTime, Date updateTime) {
        this.id = id;
        this.code = code;
        this.cabin = cabin;
        this.seatType = seatType;
        this.count = count;
        this.type = type;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Group() {
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

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin == null ? null : cabin.trim();
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType == null ? null : seatType.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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