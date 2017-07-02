package com.d4smart.my12306.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Ticket {
    private Integer id;

    private Integer orderId;

    private String trip;

    private Date date;

    private String cabin;

    private String seat;

    private String seatType;

    private String beginStation;

    private String endStation;

    private BigDecimal price;

    private String passengerName;

    private String passengerType;

    private String identityNumber;

    private String saleMethod;

    private Date saleTime;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Ticket(Integer id, Integer orderId, String trip, Date date, String cabin, String seat, String seatType, String beginStation, String endStation, BigDecimal price, String passengerName, String passengerType, String identityNumber, String saleMethod, Date saleTime, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.orderId = orderId;
        this.trip = trip;
        this.date = date;
        this.cabin = cabin;
        this.seat = seat;
        this.seatType = seatType;
        this.beginStation = beginStation;
        this.endStation = endStation;
        this.price = price;
        this.passengerName = passengerName;
        this.passengerType = passengerType;
        this.identityNumber = identityNumber;
        this.saleMethod = saleMethod;
        this.saleTime = saleTime;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Ticket() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip == null ? null : trip.trim();
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin == null ? null : cabin.trim();
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat == null ? null : seat.trim();
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType == null ? null : seatType.trim();
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName == null ? null : passengerName.trim();
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType == null ? null : passengerType.trim();
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber == null ? null : identityNumber.trim();
    }

    public String getSaleMethod() {
        return saleMethod;
    }

    public void setSaleMethod(String saleMethod) {
        this.saleMethod = saleMethod == null ? null : saleMethod.trim();
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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