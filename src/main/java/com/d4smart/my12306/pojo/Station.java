package com.d4smart.my12306.pojo;

public class Station {
    private Integer id;

    private String code;

    private String name;

    private String pinyinCode;

    private String bureau;

    private String level;

    private String region;

    private String address;

    public Station(Integer id, String code, String name, String pinyinCode, String bureau, String level, String region, String address) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.pinyinCode = pinyinCode;
        this.bureau = bureau;
        this.level = level;
        this.region = region;
        this.address = address;
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

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode == null ? null : pinyinCode.trim();
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
}