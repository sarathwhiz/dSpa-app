package com.Whiz.vaishali.deSpa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.Whiz.vaishali.deSpa.setget_category.SetGetCart;

import java.util.ArrayList;

public class CartActicity  extends AppCompatActivity {

    ListView cust_lv;
    public static  TextView total;
    double totalamount=0.00;
    double totalamountswap=0.00;
    CustomAdapterCart cust_adapter;

    private TextView user;

    ArrayList<SetGetCart> arr=new ArrayList<SetGetCart>();
    DatabaseClass db=new DatabaseClass(CartActicity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
       getSupportActionBar().hide();
        cust_lv = (ListView) findViewById(R.id.cust_listView1);

        user = (TextView)findViewById(R.id.cartuser);



        SharedPreferences sharedPreferences = getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String user_mail = sharedPreferences.getString(LoginFormconfig.NAME_SHARED_PREF, "Not Available");
        //   String user_mail = LoginForm.NAME;
        //  user.setText(" " + user_mail);

        if(LoginForm.login.equals("login")) {

            user.setText("" + user_mail);

        }

        else

        {

            user.setText("Hello guest");
            user.setTypeface(Typeface.SERIF);

        }

        total=(TextView) findViewById(R.id.total);

        cust_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                cartrefresh();
            }
        });

    }

//    @Override
//    public void onStart(){
//
//        super.onStart();
//        cartrefresh();
//    }


    @Override
    public void onResume() {

        super.onResume();  // Always call the superclass method first
//        totalamountswap=0;
//
//      //  arr.clear();
//
//        arr=db.getContacts();
//        for(int i=0;i<arr.size();i++) {
//            totalamount = Double.parseDouble(arr.get(i).getPrice_cart());
//            totalamountswap += totalamount;
//        }
//
//        CartActicity.total.setText("₹ "+""+totalamountswap);
//
//        cust_adapter = new CustomAdapterCart(CartActicity.this,arr);
//
//        cust_lv.setAdapter(cust_adapter);
//        cust_adapter.notifyDataSetChanged();

       cartrefresh();

    }

    public void cartbooking (View view)

    {
        Intent i = new Intent(CartActicity.this, BookingActivity.class);
        startActivity(i);
        // finish();
    }


    public void cartrefresh(){

        totalamountswap=0;

        arr.clear();
        arr=db.getContacts();
        for(int i=0;i<arr.size();i++) {
            totalamount = Double.parseDouble(arr.get(i).getPrice_cart());
            totalamountswap += totalamount;
        }

        CartActicity.total.setText("₹ "+""+totalamountswap);

        cust_adapter = new CustomAdapterCart(CartActicity.this,arr);

        cust_lv.setAdapter(cust_adapter);

        cust_adapter.notifyDataSetChanged();
    }

}
