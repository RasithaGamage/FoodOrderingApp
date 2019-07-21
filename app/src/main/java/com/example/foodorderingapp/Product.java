package com.example.foodorderingapp;

import java.sql.Blob;

public class Product {
    private int pro_id;
    private String pro_name;
    private String cat;
    private String brand;
    private int qty;
    private double price;
    private String details;
    private String img;

    public Product(int pro_id, String pro_name, String cat, String brand, int qty, double price, String details,String img) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.cat = cat;
        this.brand = brand;
        this.qty = qty;
        this.price = price;
        this.details = details;
        this.img = img;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImg() {return img;}

    public void setImg(String img) {this.img = img;}

}
