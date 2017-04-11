package com.Whiz.vaishali.deSpa;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterItem extends ArrayAdapter<SetGetSubCategoryItem> {

    Context classcontext;

    ArrayList<SetGetSubCategoryItem> obj;

//    int[] in = new int[] { R.drawable.image1, R.drawable.image11,
//            R.drawable.image119, R.drawable.image12, R.drawable.image13,
//            R.drawable.image14, R.drawable.image15, R.drawable.image16
//
//    };

    public CustomAdapterItem(Context context,  ArrayList<SetGetSubCategoryItem> resource) {
        super(context,R.layout.custom_adapter_item,resource);
        // TODO Auto-generated constructor stub
        this.classcontext = context;
        this.obj = resource;
       // this.obj1 = str;
    }

    private class ViewHolder {
        TextView tv, tv1;
//        ImageView imageView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) classcontext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater
                    .inflate(R.layout.custom_adapter_item, parent, false);

            String fontPath ="DancingScript_Regular.otf";
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), fontPath);




//            holder.imageView = (ImageView) convertView
//                    .findViewById(R.id.cust_imageView1);
            holder.tv = (TextView) convertView.findViewById(R.id.name);
            holder.tv1 = (TextView) convertView
                    .findViewById(R.id.price);
            holder.tv.setTypeface(tf);
            holder.tv1.setTypeface(tf);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

       // holder.tv.setText(obj[position]);
        holder.tv.setText(obj.get(position).getCategorynamesub());
        holder.tv1.setText(" ₹"+obj.get(position).getItem_pricesub());

//        holder.imageView.setImageResource(in[position]);
//
//        holder.imageView.setOnClickListener( new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//
//                Toast.makeText(getContext(), "application process", Toast.LENGTH_LONG).show();
//            }
//        });

        // holder.imageView.setOnClickListener(new OnClickListener() {
        //
        // @Override
        // public void onClick(View arg0) {
        // // TODO Auto-generated method stub
        // Toast.makeText(getContext(), "hi image position " + position,
        // Toast.LENGTH_LONG).show();
        // }
        // });

        return convertView;

    }

}