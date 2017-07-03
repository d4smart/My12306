package com.d4smart.my12306.pojo;

import com.d4smart.my12306.common.Const;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class User {
    private Integer id;

    private String phone;

    private String identityNumber;

    private String email;

    private String password;

    private String actualName;

    private String sex;

    private Integer role;

    private Date lastLogin;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public User(Integer id, String phone, String identityNumber, String email, String password, String actualName, String sex, Integer role, Date lastLogin, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.phone = phone;
        this.identityNumber = identityNumber;
        this.email = email;
        this.password = password;
        this.actualName = actualName;
        this.sex = sex;
        this.role = role;
        this.lastLogin = lastLogin;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", identityNumber='" + identityNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", actualName='" + actualName + '\'' +
                ", sex='" + sex + '\'' +
                ", role=" + role +
                ", lastLogin=" + lastLogin +
                ", status=" + status +
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber == null ? null : identityNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName == null ? null : actualName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
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

    @JsonIgnore
    public Boolean isAdmin() {
        return this != null && role == Const.Role.ROLE_ADMIN;
    }
}