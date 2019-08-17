package com.example.foodorderingapp;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

//class CartItem {
//
//    private String itemId;
//    private int itemAmount;
//
//    public CartItem(String itemId, int itemAmount) {
//        this.itemId = itemId;
//        this.itemAmount = itemAmount;
//    }
//
//    public String getItemId() {
//        return itemId;
//    }
//
//    public void setItemId(String itemId) {
//        this.itemId = itemId;
//    }
//
//    public int getItemAmount() {
//        return itemAmount;
//    }
//
//    public void setItemAmount(int itemAmount) {
//        this.itemAmount = itemAmount;
//    }
//}


class CartItem extends Product{

    private int buying_amount;

    public CartItem(int pro_id, String pro_name, String cat, String sub_cat, String brand, int qty, double price, String details, String img,int buying_amount) {
        super(pro_id, pro_name, cat, sub_cat, brand, qty, price, details, img);
        this.buying_amount = buying_amount;
    }


    public int getBuying_amount() {
        return buying_amount;
    }

    public void setBuying_amount(int buying_amount) {
        this.buying_amount = buying_amount;
    }

}

class  ShoppingCart {

    private static List<CartItem> shoppingCartArray;
    private static ShoppingCart single_instance = null;

    private ShoppingCart() {
    }

    public static ShoppingCart getInstance()
    {
        if (single_instance == null)
        {
            single_instance = new ShoppingCart();
            shoppingCartArray = new ArrayList<CartItem>();
        }

        return single_instance;
    }

    public ShoppingCart(List<CartItem> shoppingCartArray) {
        this.shoppingCartArray = shoppingCartArray;
    }

    public List<CartItem> getShoppingCartArray() {
        return shoppingCartArray;
    }

    public void setShoppingCartArray(List<CartItem> shoppingCartArray) {
        for(CartItem i:shoppingCartArray
        ) {
            this.shoppingCartArray.add(i);
        }

    }

}