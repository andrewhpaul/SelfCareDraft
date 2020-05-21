package com.a_adevelopers.selfcare.Model;

import java.util.ArrayList;

public class Model extends ArrayList {

    String id, image, name, purchaseDate, expiryDate, url, price, location, productGroup, tag, notes, rating;

    public Model(String id, String image, String name, String purchaseDate, String expiryDate, String url, String price, String location, String productGroup, String tag, String notes, String rating) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
        this.url = url;
        this.price = price;
        this.location = location;
        this.productGroup = productGroup;
        this.tag = tag;
        this.notes = notes;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getUrl() {
        return url;
    }

    public String getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getGroup() {
        return productGroup;
    }

    public String getTag() {
        return tag;
    }

    public String getNotes() {
        return notes;
    }

    public String getRating() {
        return rating;
    }
}

