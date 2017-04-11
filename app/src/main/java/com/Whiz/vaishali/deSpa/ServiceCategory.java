package com.Whiz.vaishali.deSpa;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *
 */
public class ServiceCategory {

    private static final String CATEGORIES_ID = "categories_id";
    private static final String CATEGORIES_NAME = "categories_name";


    public int categoriesId;
    public String categoriesName;

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public static ServiceCategory fromJsonObject(JSONObject jsonObject) {
        ServiceCategory serviceCategory = new ServiceCategory();
        try {
            serviceCategory.categoriesId = jsonObject.getInt(CATEGORIES_ID);
            serviceCategory.categoriesName = jsonObject.getString(CATEGORIES_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return serviceCategory;
    }

    public static ArrayList<ServiceCategory> fromJsonArray(String jsonArray) {
        ArrayList<ServiceCategory> listServiceCategories = new ArrayList<>();

        try {
            JSONArray jsonServiceCategories = new JSONArray(jsonArray);
            for (int i = 0; i < jsonServiceCategories.length(); i++) {
                ServiceCategory serviceCategory = fromJsonObject(jsonServiceCategories
                        .getJSONObject(i));
                if(serviceCategory != null) {
                    listServiceCategories.add(serviceCategory);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listServiceCategories;
    }
}
