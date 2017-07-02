package com.d4smart.my12306.pojo;

import java.util.Date;

public class Station {
    private Integer id;

    private String name;

    private String code;

    private String bureau;

    private String level;

    private String region;

    private String address;

    private Date createTime;

    private Date updateTime;

    public Station(Integer id, String name, String code, String bureau, String level, String region, String address, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.bureau = bureau;
        this.level = level;
        this.region = region;
        this.address = address;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Station() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBureau() {
        return bureau;
    }

    public void setBureau(String bureau) {
        this.bureau = bureau == null ? null : bureau.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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