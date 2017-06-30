package com.d4smart.my12306.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String phone;

    private String identityNumber;

    private String email;

    private String password;

    private String actualName;

    private String sex;

    private Byte isAdmin;

    private Date lastLogin;

    private Integer status;

    public User(Integer id, String phone, String identityNumber, String email, String password, String actualName, String sex, Byte isAdmin, Date lastLogin, Integer status) {
        this.id = id;
        this.phone = phone;
        this.identityNumber = identityNumber;
        this.email = email;
        this.password = password;
        this.actualName = actualName;
        this.sex = sex;
        this.isAdmin = isAdmin;
        this.lastLogin = lastLogin;
        this.status = status;
    }

    public User() {
        super();
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

    public Byte getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Byte isAdmin) {
        this.isAdmin = isAdmin;
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
}