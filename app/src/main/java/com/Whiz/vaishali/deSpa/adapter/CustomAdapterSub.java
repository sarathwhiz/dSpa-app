package com.Whiz.vaishali.deSpa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Whiz.vaishali.deSpa.R;
import com.Whiz.vaishali.deSpa.setget_category.SetterGetAppointment;

import java.util.ArrayList;


public class CustomAdapterSub extends ArrayAdapter<String> {

        Context classcontext;
        String[] obj, obj1;


private ArrayList<String> data = new ArrayList<String>();

        ArrayList<SetterGetAppointment> cart;

public CustomAdapterSub(Context context, ArrayList<SetterGetAppointment> cart) {
        super(context, R.layout.custom_sub_list);
        // TODO Auto-generated constructor stub
        classcontext = context;
        this.cart = cart;

        }

@Override
public int getCount() {
        return cart.size();
        }

private class ViewHolder {
    TextView tv1,tv,tv0;
    ImageView imageView;
    ListView lv;
}

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) classcontext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.custom_sub_list, parent, false);


            holder.tv1 = (TextView) convertView.findViewById(R.id.Serviceid);
            holder.tv = (TextView) convertView.findViewById(R.id.ServiceN);
            holder.tv0 = (TextView) convertView.findViewById(R.id.ServiceP);

        //    holder.lv = (ListView)convertView.findViewById(R.id.listservices);

            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv1.setText(cart.get(position).getServid());
        holder.tv.setText(cart.get(position).getItemname());
        holder.tv0.setText(cart.get(position).getPrice());

        return convertView;

    }
}
