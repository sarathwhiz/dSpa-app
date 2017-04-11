package com.Whiz.vaishali.deSpa;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Whiz.vaishali.deSpa.setget_category.SetGetCart;

import java.util.ArrayList;


public class CustomAdapterBooking extends ArrayAdapter<SetGetCart> {

    Context classcontext;
    double total;

    //  private CustomAdapterCart adapter;

    ArrayList<SetGetCart> arr=new ArrayList<SetGetCart>();
    DatabaseClass db=new DatabaseClass(getContext());

    BookingActivity bookingActivity;

    public CustomAdapterBooking(Context contex, ArrayList<SetGetCart> arr) {
        super(contex, R.layout.custom_adapter_cart, arr);

        // TODO Auto-generated constructor stub

        classcontext = contex;
        this.arr = arr;
        //  this.adapter = this;

        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView tv, tv1,total;
        ImageView cancel;
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
                    .inflate(R.layout.custom_adapter_cart, parent, false);

            String fontPath ="DancingScript_Regular.otf";
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), fontPath);
//            holder.imageView = (ImageView) convertView
//                    .findViewById(R.id.cust_imageView1);
            holder.tv = (TextView) convertView.findViewById(R.id.cartname);
            holder.tv1 = (TextView) convertView
                    .findViewById(R.id.cartprice);

            holder.tv.setTypeface(tf);
            holder.tv1.setTypeface(tf);

            holder.cancel = (ImageView)convertView.findViewById(R.id.cartcancel);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv.setText(arr.get(position).getName_cart());

        holder.tv1.setText(arr.get(position).getPrice_cart());

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.deleteContacts(arr.get(position).getId());

//                if(classcontext instanceof BookingActivity){
//                    ((BookingActivity)classcontext). bookingrefresh();
//                }
//
//                notifyDataSetChanged();


                if(classcontext instanceof BookingActivity){
                    ((BookingActivity) classcontext).bookingrefresh();

                    notifyDataSetChanged();
                }
            }
        });

        return convertView;


    }

}
