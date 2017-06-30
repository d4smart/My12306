package com.d4smart.my12306.pojo;

public class Region {
    private Integer id;

    private String name;

    public Region(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Region() {
        super();
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
}