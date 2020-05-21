package com.a_adevelopers.selfcare.Model;

public class GroupModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public GroupModel(String id, String name, String day) {
        this.name = name;
        this.id = id;
        this.day = day;
    }

    String name;
    String id;
    String day;
}
