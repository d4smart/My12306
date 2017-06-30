package com.d4smart.my12306.pojo;

public class Bureau {
    private Integer id;

    private String name;

    public Bureau(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Bureau() {
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