package com.d4smart.my12306.pojo;

public class Group {
    private Integer id;

    private String code;

    private String cabin;

    private String seatType;

    private Integer count;

    private String type;

    public Group(Integer id, String code, String cabin, String seatType, Integer count, String type) {
        this.id = id;
        this.code = code;
        this.cabin = cabin;
        this.seatType = seatType;
        this.count = count;
        this.type = type;
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
}