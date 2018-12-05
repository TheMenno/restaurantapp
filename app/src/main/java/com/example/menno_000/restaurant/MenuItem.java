package com.example.menno_000.restaurant;

public class MenuItem {
    private String name;
    private String description;
    private String imageurl;
    private int price;
    private String category;

    public MenuItem(String name, String description, String imageurl, int price, String category) {
        this.name = name;
        this.description = description;
        this.imageurl = imageurl;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
