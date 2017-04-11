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
import android.widget.Toast;

import com.Whiz.vaishali.deSpa.setget_category.SetGetCart;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MenListItem extends AppCompatActivity  {

    private String Tag = MainActivity.class.getSimpleName();
    private ListView mLvServiceCategories;
    private ArrayList<ServiceCategory> mListServiceCategory;
    private int mCategory;
    DatabaseClass db=new DatabaseClass(MenListItem.this);
    ArrayList<SetGetSubCategoryItem> setlist= new ArrayList<SetGetSubCategoryItem>();
    String mstring;
    private TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.men_item_activity);
        getSupportActionBar().hide();
        Bundle extras = getIntent().getExtras();


        user = (TextView)findViewById(R.id.itemuser);

        SharedPreferences sharedPreferences = getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String user_mail = sharedPreferences.getString(LoginFormconfig.NAME_SHARED_PREF, "Not Available");
      //  String user_name = LoginForm.NAME;
     //   user.setText(" " + user_mail);

        if(LoginForm.login.equals("login")) {


            user.setText("" + user_mail);

        }else{


            user.setText("Hello guest");
            user.setTypeface(Typeface.SERIF);

        }

        mstring = extras.getString("id");

     //   Toast.makeText(MenListItem.this, ""+mstring, Toast.LENGTH_SHORT).show();

        if(getIntent().getExtras() != null) {
            mCategory = getIntent().getIntExtra(AppConstants.CATEGORIES_ID, Integer.parseInt(mstring));
        }
        mLvServiceCategories = (ListView) findViewById(R.id.listmenthread);

        new GetJobs().execute();
    }

//    private class GetJobs extends AsyncTask<Void, Void, ArrayList<SetGetSubCategory>>

    private class GetJobs extends AsyncTask<Void, Void, ArrayList<SetGetSubCategoryItem>> {
        private ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(MenListItem.this, "", "Please wait...Loading",
                    false, true);
        }

        @Override
        protected ArrayList<SetGetSubCategoryItem> doInBackground(Void... arg0) {
            String requestGenderCategory = new GenderCategory(Integer.valueOf(mstring)).getJson();
            String response = NetworkHandler.doPost(URLConstants.URL_DESPA_ITEM_DETAILS, requestGenderCategory);

            if(response != null) {

                // [{"sub_categories_id":"5","sub_categories_name":"Rebonding and Smoothening"},{"sub_categories_id":"6","sub_categories_name":"Keratin Spa Treatment"},{"sub_categories_id":"7","sub_categories_name":"Renew 'C'"},{"sub_categories_id":"8","sub_categories_name":"Hair Spa"},{"sub_categories_id":"10","sub_categories_name":"Scalp and Hair Treatment"},{"sub_categories_id":"9","sub_categories_name":"Hair Styles"},{"sub_categories_id":"11","sub_categories_name":"Keratin Treatment"},{"sub_categories_id":"12","sub_categories_name":"Global Coloring"},{"sub_categories_id":"15","sub_categories_name":"Heena"},{"sub_categories_id":"14","sub_categories_name":"Color Retouch"},{"sub_categories_id":"13","sub_categories_name":"Inoa Global Coloring"},{"sub_categories_id":"16","sub_categories_name":"Highlightning"}]

                try {

                    JSONArray jsonarray = new JSONArray(response);

                    // SetGet_main setGet_main=new SetGet_main();
                    for (int i = 0; i < jsonarray.length(); i++) {

                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        SetGetSubCategoryItem setGet_main=new SetGetSubCategoryItem();
                        setGet_main.setId(jsonobject.getString("items_id"));
                        setGet_main.SetCategorynamesub(jsonobject.getString("items_name"));
                        setGet_main.setItem_pricesub(jsonobject.getString("items_price"));

                        setlist.add(setGet_main);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return setlist;
        }

        @Override
        protected void onPostExecute(final ArrayList<SetGetSubCategoryItem> result) {
            super.onPostExecute(result);
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            CustomAdapterItem adapter = new CustomAdapterItem(MenListItem.this, result);

            mLvServiceCategories.setAdapter(adapter);

            mLvServiceCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String string = result.get(position).getId();
                    db.addContacts(new SetGetCart(result.get(position).getCategorynamesub(),result.get(position).getItem_pricesub()));
       //             Intent intent = new Intent(MenListItem.this,CartActicity.class);

                    Toast.makeText(MenListItem.this, "Selected item added to cart", Toast.LENGTH_SHORT).show();

//                  //  intent.putExtra("id",result.get(position).getId());
//
                }
            });
        }
    }

    public String readResponse(HttpResponse res) {
        InputStream is=null;
        String return_text="";
        try {
            is=res.getEntity().getContent();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
            String line="";
            StringBuffer sb=new StringBuffer();
            while ((line=bufferedReader.readLine())!=null)
            {
                sb.append(line);
            }
            return_text=sb.toString();
        } catch (Exception e)
        {

        }
        return return_text;
    }

    public void itemcart(View view)
    {
        Intent i = new Intent(MenListItem.this, CartActicity.class);
        startActivity(i);
        finish();

    }
//
//    public void backtoregular(View view)
//
//    {
//        Intent i = new Intent(MenListItem.this, RegularWomens.class);
//        startActivity(i);
//
//    }

    public void goback(View view){

        Intent i = new Intent(MenListItem.this,RegularMens.class);
        startActivity(i);

    }
}

