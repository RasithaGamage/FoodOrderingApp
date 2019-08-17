package com.example.foodorderingapp;

public class RowItem {

    private int num;
    private String Pro_name;
    private String pic;
    private String details;
    private String contactType;
    private int amount;

    public RowItem(int num, String pro_name, String pic, String details,
                   String contactType, int amount) {

        this.num = num;
        this.Pro_name = pro_name;
        this.pic = pic;
        this.details = details;
        this.contactType = contactType;
        this.amount = amount;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPro_name() {
        return Pro_name;
    }

    public void setPro_name(String member_name) {
        this.Pro_name = member_name;
    }

    public String getPic() {return pic;}

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {this.details = details;}

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}