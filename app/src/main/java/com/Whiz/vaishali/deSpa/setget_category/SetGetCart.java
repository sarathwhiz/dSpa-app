package com.Whiz.vaishali.deSpa.setget_category;


public class SetGetCart {


    private int id;
    private String name_cart;
    private String price_cart;

    public SetGetCart(String categorynamesub, String item_pricesub) {
        this.name_cart=categorynamesub;
        this.price_cart=item_pricesub;
    }

    public SetGetCart() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_cart() {
        return name_cart;
    }

    public void setName_cart(String name_cart) {
        this.name_cart = name_cart;
    }

    public String getPrice_cart() {
        return price_cart;
    }

    public void setPrice_cart(String price_cart) {
        this.price_cart = price_cart;
    }


}
