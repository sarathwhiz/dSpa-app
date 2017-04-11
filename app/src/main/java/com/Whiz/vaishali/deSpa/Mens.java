package com.Whiz.vaishali.deSpa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Mens extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView mLvServiceCategories;
    private ArrayList<ServiceCategory> mListServiceCategory;
    private int mCategory;

    private TextView user;

    //ArrayList<SetGet_main> setnewlist= new ArrayList<SetGet_main>();

    ArrayList<SetGet_main> setlist = new ArrayList<SetGet_main>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mens);
        getSupportActionBar().hide();

        user = (TextView)findViewById(R.id.menuser);

        SharedPreferences sharedPreferences = getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
       // String user_mail = sharedPreferences.getString(LoginFormconfig.EMAIL_SHARED_PREF, "Not Available");
        String user_Name = sharedPreferences.getString(LoginFormconfig.NAME_SHARED_PREF, "Not Available");

      //  String user_Name = LoginForm.NAME;

         //   user.setText(" " + user_mail);

        if(LoginForm.login.equals("login")) {


            user.setText("" + user_Name);

        }else{


            user.setText("Hello guest");
            user.setTypeface(Typeface.SERIF);

        }


        if(getIntent().getExtras() != null) {
            mCategory = getIntent().getIntExtra(AppConstants.CATEGORIES_ID, AppConstants.GENDER_ID_MEN);
        }
        mLvServiceCategories = (ListView) findViewById(R.id.listmens);

        new GetJobs().execute();
    }

        private class GetJobs extends AsyncTask<Void, Void, ArrayList<SetGet_main>> {
        private ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(Mens.this, "", "Please wait...Loading",
                    false, true);
        }

        @Override
        protected ArrayList<SetGet_main> doInBackground(Void... arg0) {
            String requestGenderCategory = new GenderCategory(AppConstants.GENDER_ID_MEN).getJson();
            String response = NetworkHandler.doPost(URLConstants.URL_DESPA_CATEGORIES, requestGenderCategory);

            if(response != null) {

                // mListServiceCategory = ServiceCategory.fromJsonArray(response);
                //       [{"categories_id":"1","categories_name":"Regular"},{"categories_id":"2","categories_name":"Hair Care"},{"categories_id":"3","categories_name":"Hands and Feet"},{"categories_id":"4","categories_name":"Skin Care"},{"categories_id":"5","categories_name":"Groom Makeovers"},{"categories_id":"6","categories_name":"Luxury Spa Services"}]

                try {

                    JSONArray jsonarray = new JSONArray(response);

                    // SetGet_main setGet_main=new SetGet_main();
                    for (int i = 0; i < jsonarray.length(); i++) {

                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        SetGet_main setGet_main=new SetGet_main();
                        setGet_main.setId(jsonobject.getString("categories_id"));
                        setGet_main.setName(jsonobject.getString("categories_name"));

                        setlist.add(setGet_main);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return setlist;
        }

        @Override
        protected void onPostExecute(final ArrayList<SetGet_main> result) {
            super.onPostExecute(result);
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            ServiceCategoryAdapter adapter = new ServiceCategoryAdapter(Mens.this, result);
            mLvServiceCategories.setAdapter(adapter);

            mLvServiceCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String string = result.get(position).getId();
                    Intent intent = new Intent(Mens.this,RegularMens.class);
                    intent.putExtra("id",result.get(position).getId());
                    startActivity(intent);
                   // finish();
                }
            });
        }
    }

//    public void onBackPressed() {
//
//        Intent i = new Intent(Mens.this,TheDeSpaStudio.class);
//        startActivity(i);
//        finish();
//    }

}