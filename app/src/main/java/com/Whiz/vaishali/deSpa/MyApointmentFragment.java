package com.Whiz.vaishali.deSpa;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.Whiz.vaishali.deSpa.adapter.CustomAdapterN;
import com.Whiz.vaishali.deSpa.setget_category.SetterGetAppointment;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MyApointmentFragment extends Fragment {

    public static final String URL = "http://app.despastudio.com/my_appointment.php";
    ArrayList<SetterGetAppointment> cart = new ArrayList<SetterGetAppointment>();
    ListView cust_lv;
    TextView tvapp;
    TextView tvapp1;
    TextView tvapp2;
    TextView tvapp3;
    TextView tvapp4;

    private ProgressDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_my_apointment_fragment, container, false);

        cust_lv = (ListView) view.findViewById(R.id.cust_listView3);

        tvapp=(TextView)view.findViewById(R.id.myapptext);
        tvapp1=(TextView)view.findViewById(R.id.myapptext2);
        tvapp2=(TextView)view.findViewById(R.id.myapptext3);
        tvapp3=(TextView)view.findViewById(R.id.myapptext4);
        tvapp4=(TextView)view.findViewById(R.id.myapptext5);

        tvapp.setVisibility(View.INVISIBLE);
        tvapp1.setVisibility(View.INVISIBLE);
        tvapp2.setVisibility(View.INVISIBLE);
        tvapp3.setVisibility(View.INVISIBLE);
        tvapp4.setVisibility(View.INVISIBLE);

        if(LoginForm.login.equals("login")) {

            new CartList().execute();

        }else
        {
            if(LoginForm.guest.equals("guest")){

               cust_lv.setVisibility(View.GONE);
                tvapp.setVisibility(View.VISIBLE);
                tvapp1.setVisibility(View.VISIBLE);
            }
        }

        return view;

    }

    private ProgressDialog progressDialog = null;

    private class CartList extends AsyncTask<Void ,Void ,ArrayList<SetterGetAppointment>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getActivity(), "", "Please wait...Loading",
                    false, true);

        }

        @Override
        protected ArrayList<SetterGetAppointment> doInBackground(Void... params) {


           SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
     String responseID = sharedPreferences.getString(LoginFormconfig.ID_SHARED_PREF, "id");
    //          String responseID= LoginForm.IDD;

     String responseIDnew = new SetterGetAppointment(responseID).getJson();

     //       String responseID = new SetterGetAppointment(IDClass.ID_LIST).getJson();
            String response = NetworkHandler.doPostin(URL,responseIDnew);

            if(response!=null){

                try {
                    JSONArray jsonarray = new JSONArray(response);

                    for (int i = 0; i < jsonarray.length(); i++)
                    {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);

                        JSONObject jsonObjectone= jsonobject.getJSONObject("appointment_details");
                        SetterGetAppointment setGetCart= new SetterGetAppointment();

                        setGetCart.setIdd(jsonObjectone.getString("id"));
                        setGetCart.setName(jsonObjectone.getString("name"));
                        setGetCart.setMobile(jsonObjectone.getString("mobile_no"));
                        setGetCart.setEmail(jsonObjectone.getString("email"));
                        setGetCart.setDate(jsonObjectone.getString("req_date"));
                        setGetCart.setTime(jsonObjectone.getString("req_time"));
                        setGetCart.setTotal(jsonObjectone.getString("total"));

                           cart.add(setGetCart);

//***************************************************************************************************************************


                        JSONArray jsonArray = jsonobject.getJSONArray("cart");

                       for(int j=0;j<jsonArray.length();j++) {

                           JSONObject jsonobject3 = jsonArray.getJSONObject(j);

                           setGetCart.setItemname(jsonobject3.getString("items_name"));
                           setGetCart.setPrice(jsonobject3.getString("price"));

                       }
//
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

            CustomAdapterN adapter = new CustomAdapterN(getActivity(), result);

            cust_lv.setAdapter(adapter);

            cust_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {

                    //	String string = result.get(position).getId();
                }
            });

            ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (result.size() <= 0) {

                    if (networkInfo != null && networkInfo.isConnected()) {

                        cust_lv.setVisibility(View.GONE);
                        tvapp.setVisibility(View.GONE);
                        tvapp1.setVisibility(View.GONE);
                        tvapp2.setVisibility(View.VISIBLE);
                    }
                    else {
                        TastyToast.makeText(getActivity(), "Please check your internet Connection", Toast.LENGTH_LONG,TastyToast.ERROR);

                        cust_lv.setVisibility(View.GONE);
                        tvapp.setVisibility(View.GONE);
                        tvapp1.setVisibility(View.GONE);
                        tvapp3.setVisibility(View.VISIBLE);
                        tvapp4.setVisibility(View.VISIBLE);
                    }
                }
            }
    }

    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getActivity().finish();
                        Intent intent = new Intent(getActivity(), TheDeSpaStudio.class);
                        startActivity(intent);

                        return true;
                    }
                }
                return false;
            }
        });
    }
}