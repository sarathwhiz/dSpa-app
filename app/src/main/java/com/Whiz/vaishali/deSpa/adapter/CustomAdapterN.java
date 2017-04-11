package com.Whiz.vaishali.deSpa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.Whiz.vaishali.deSpa.MyAppointmentActivity;
import com.Whiz.vaishali.deSpa.R;
import com.Whiz.vaishali.deSpa.setget_category.SetterGetAppointment;

import java.util.ArrayList;

public class CustomAdapterN extends ArrayAdapter<String> {

    Context classcontext;

   // String[] obj, obj1;

    //ArrayList<SetterGetAppointment> cart = new ArrayList<SetterGetAppointment>();
    ArrayList<SetterGetAppointment> cart;

    public CustomAdapterN(Context context, ArrayList<SetterGetAppointment> cart) {
        super(context, R.layout.custom_my_appointment);
        // TODO Auto-generated constructor stub
        classcontext = context;
        this.cart = cart;

    }

    @Override
    public int getCount() {
        return cart.size();
    }

    private class ViewHolder {
        TextView tv,tv0,tv1,tv2,tv3,tv4,tv5;
        Button btnn;
      //  ImageView imageView;
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
                    .inflate(R.layout.custom_my_appointment, parent, false);

            holder.tv0 = (TextView) convertView.findViewById(R.id.tid);
            holder.tv = (TextView)  convertView.findViewById(R.id.tname);
            holder.tv3 = (TextView) convertView.findViewById(R.id.tmobile);
            holder.tv4 = (TextView) convertView.findViewById(R.id.temail);
            holder.tv1 = (TextView) convertView.findViewById(R.id.tdate);
            holder.tv2 = (TextView) convertView.findViewById(R.id.ttime);
            holder.tv5 = (TextView) convertView.findViewById(R.id.itemt);
            holder.btnn = (Button)  convertView.findViewById(R.id.btn);

            convertView.setTag(holder);
        }
        else

        {
            holder = (ViewHolder) convertView.getTag();
        }

//		holder.tv.setText(obj[position]);
//		holder.tv1.setText(obj1[position]);

        holder.tv.setText(cart.get(position).getName());
        holder.tv0.setText(cart.get(position).getIdd());
        holder.tv1.setText(cart.get(position).getDate());
        holder.tv2.setText(cart.get(position).getTime());
        holder.tv3.setText(cart.get(position).getMobile());
        holder.tv4.setText(cart.get(position).getEmail());
        holder.tv5.setText("₹"+cart.get(position).getTotal());

        holder.btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getContext(),MyAppointmentActivity.class);

                i.putExtra("team1",position);
                getContext().startActivity(i);

            }
        });

        return convertView;

    }

}
