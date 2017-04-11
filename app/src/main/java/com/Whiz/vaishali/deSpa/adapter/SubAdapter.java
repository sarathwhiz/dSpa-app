package com.Whiz.vaishali.deSpa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.Whiz.vaishali.deSpa.setget_category.SetGetSubCategory;

import java.util.ArrayList;



public class SubAdapter extends ArrayAdapter<SetGetSubCategory> {

    private Context mContext;
    private ArrayList<SetGetSubCategory> mCategoryArrayList;

    public SubAdapter(Context context, ArrayList<SetGetSubCategory> result) {
        super(context, android.R.layout.simple_list_item_1,result);
        this.mContext=context;
        this.mCategoryArrayList=result;

    }



    @Override
    public int getCount() {
        return mCategoryArrayList.size();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) convertView;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            String fontPath ="DancingScript_Regular.otf";

            view = (TextView) inflater.inflate(android.R.layout.simple_list_item_1, null);
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), fontPath);

            view.setTypeface(tf);
            view.setTextColor(Color.WHITE);
            view.setTextSize(21);


        }
        view.setText(mCategoryArrayList.get(position).getCategorynamesub());
        convertView = view;
        return convertView;
    }

}
