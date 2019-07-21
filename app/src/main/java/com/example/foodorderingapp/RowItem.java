package com.example.foodorderingapp;

public class RowItem {

    private int num;
    private String Pro_name;
    private int pic_id;
    private String details;
    private String contactType;

    public RowItem(int num, String member_name, int pic_id, String details,
                   String contactType) {

        this.num = num;
        this.Pro_name = member_name;
        this.pic_id = pic_id;
        this.details = details;
        this.contactType = contactType;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMember_name() {
        return Pro_name;
    }

    public void setMember_name(String member_name) {
        this.Pro_name = member_name;
    }

    public int getPic_id() {return pic_id;}

    public void setPic_id(int profile_pic_id) {
        this.pic_id = profile_pic_id;
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

}