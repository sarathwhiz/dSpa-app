package com.Whiz.vaishali.deSpa;

public class SetGetSubCategoryItem {
    private String Item_id;
    private String item_namesub;
    private String item_pricesub;
    private String id;

    public String getItem_pricesub() {
        return item_pricesub;
    }

    public void setItem_pricesub(String item_pricesub) {
        this.item_pricesub = item_pricesub;
    }

    public String getSub_id() {

        return Item_id;
    }

    public void setSub_id(String sub_id) {
        this.Item_id = sub_id;
    }

    public String getCategorynamesub() {
        return item_namesub;
    }

    public void SetCategorynamesub(String categorynamesub) {
        this.item_namesub = categorynamesub;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
