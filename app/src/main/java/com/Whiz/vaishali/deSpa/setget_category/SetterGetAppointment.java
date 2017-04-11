package com.Whiz.vaishali.deSpa.setget_category;

import org.json.JSONException;
import org.json.JSONObject;


public class SetterGetAppointment {


    private int id;

    private String newid;

    private String servid;
    private String name;
    private String idd;
    private String date;
    private String time;
    private String itemname;
    private String mobile;
    private String email;
    private String price;
    private String total;

    private  String ID = "id";
    public SetterGetAppointment() {

    }

//    public SetterGetAppointment(int id) {
//
//        this.id=id;
//    }

    public SetterGetAppointment(String newid) {

        this.newid=newid;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServid() {
        return servid;
    }

    public void setServid(String servid) {
        this.servid = servid;
    }

    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }

    public String getJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(ID, newid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    public String getNewid() {
        return newid;
    }

    public void setNewid(String newid) {
        this.newid = newid;
    }






}
