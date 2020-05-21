package com.a_adevelopers.selfcare.Model;

public class NotiModel {
    public NotiModel(String id,String image,String name, String expDate, String selectedExpDate) {
        this.name = name;
        this.expDate = expDate;
        this.selectedExpDate = selectedExpDate;
        this.id=id;
        this.image=image;
    }

    String name;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getSelectedExpDate() {
        return selectedExpDate;
    }

    public void setSelectedExpDate(String selectedExpDate) {
        this.selectedExpDate = selectedExpDate;
    }

    String expDate;
    String selectedExpDate;
}
