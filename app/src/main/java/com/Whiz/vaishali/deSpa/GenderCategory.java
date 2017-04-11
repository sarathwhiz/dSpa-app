package com.Whiz.vaishali.deSpa;

import org.json.JSONException;
import org.json.JSONObject;

public class GenderCategory {

    private  String ID = "id";

    private int id;

    public GenderCategory(int id) {
        this.id = id;
    }

    public GenderCategory(String mstring) {
        ID = mstring;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(ID, id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
