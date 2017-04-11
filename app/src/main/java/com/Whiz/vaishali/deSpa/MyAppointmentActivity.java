package com.Whiz.vaishali.deSpa;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.Whiz.vaishali.deSpa.adapter.CustomAdapterSub;
import com.Whiz.vaishali.deSpa.setget_category.SetterGetAppointment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyAppointmentActivity extends AppCompatActivity {
    public static final String URL = "http://app.despastudio.com/my_appointment.php";
    ListView cust_lv;


    ArrayList<SetterGetAppointment> cart = new ArrayList<SetterGetAppointment>();
    private ProgressDialog pDialog;

    String app_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointment);

        cust_lv = (ListView) findViewById(R.id.cartlist);


        CustomAdapterSub adapter = new CustomAdapterSub(MyAppointmentActivity.this, cart);

        cust_lv.setAdapter(adapter);

        new CartList().execute();
    }
    private ProgressDialog progressDialog = null;
    private class CartList extends AsyncTask<Void ,Void ,ArrayList<SetterGetAppointment>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MyAppointmentActivity.this, "", "Please wait...Loading",
                    false, true);
        }

        @Override
        protected ArrayList<SetterGetAppointment> doInBackground(Void... params) {

            SharedPreferences sharedPreferences = getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            String responseID = sharedPreferences.getString(LoginFormconfig.ID_SHARED_PREF, "id");

            String responseIDnew = new SetterGetAppointment(responseID).getJson();


            //       String responseID = new SetterGetAppointment(IDClass.ID_LIST).getJson();
            String response = NetworkHandler.doPostin(URL,responseIDnew);

            Intent intent = getIntent();
            //   String product = intent.getStringExtra("team1");

            int q = intent.getIntExtra("team1",0);

            String qq=String.valueOf(q);

            if(response!=null){

                try {
                    JSONArray jsonarray = new JSONArray(response);

                    //   JSONObject jsonObjectone= new JSONObject(product);

                    for (int i = 0; i < jsonarray.length(); i++)

                    {

                        JSONObject jsonobject = jsonarray.getJSONObject(i);

                        JSONArray jsonArray = jsonobject.getJSONArray("cart");
                        for(int j=0;j<jsonArray.length();j++) {

                            JSONObject jsonobject3 = jsonArray.getJSONObject(j);

//                            setGetCart.setItemname(jsonobject3.getString("items_name"));
//                            setGetCart.setPrice(jsonobject3.getString("price"));
                            //                    setGetCart.setAppid(jsonobject3.getString("appointment_id"));
                            //                    String app_id=jsonobject3.getString("appointment_id");

                            app_id = jsonarray.getJSONObject(q).getString("cart");

                            // String app_id = jsonobject3.getString("items_name");

                            String cart_id=jsonarray.getJSONObject(q).getString("cart");

                        }

                    }

                    if(app_id!=null)

                    {
                        JSONArray jsonapp = new JSONArray(app_id);

                        for (int r=0;r<jsonapp.length();r++){

                            JSONObject jsonObjectapp=jsonapp.getJSONObject(r);

                            SetterGetAppointment setGetCart= new SetterGetAppointment();

                            setGetCart.setServid(jsonObjectapp.getString("appointment_id"));
                            setGetCart.setItemname(jsonObjectapp.getString("items_name"));
                            setGetCart.setPrice(jsonObjectapp.getString("price"));

                            cart.add(setGetCart);

                        }

                    }
                }

                catch (JSONException e)

                {
                    e.printStackTrace();
                }
            }


            return cart;
        }

        @Override
        protected void onPostExecute(final ArrayList<SetterGetAppointment> result)
        {

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Intent intent = getIntent();
            //     String product = intent.getStringExtra("team1");
            int q = intent.getIntExtra("team1",0);

            String qq=String.valueOf(q);

            Toast.makeText(getApplication(), ""+q, Toast.LENGTH_SHORT).show();

            CustomAdapterSub adapter = new CustomAdapterSub(MyAppointmentActivity.this, result);

            cust_lv.setAdapter(adapter);

            cust_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    // TODO Auto-generated method stub
                    //	String string = result.get(position).getId();

                    ColorDrawable[] color2 = {
                            new ColorDrawable(Color.parseColor("#e07a9df4")),
                            new ColorDrawable(Color.parseColor("#ffffff"))
                    };
                    TransitionDrawable trans2 = new TransitionDrawable(color2);
                    arg1.setBackground(trans2);
                    trans2.startTransition(2000);

                }
            });


        }
    }
}
