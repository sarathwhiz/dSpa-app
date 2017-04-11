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

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RegularWomens extends AppCompatActivity  implements AdapterView.OnItemClickListener {
    private String TAG = MainActivity.class.getSimpleName();
    private ListView mLvServiceCategories;
    private ArrayList<ServiceCategory> mListServiceCategory;
    private int mCategory;
    ArrayList<SetGetSubCategoryWomen> setlist= new ArrayList<SetGetSubCategoryWomen>();
    String mstring;

    private TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regular_womens);
        getSupportActionBar().hide();


        user = (TextView)findViewById(R.id.reglwomenuser);

        SharedPreferences sharedPreferences = getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String user_mail = sharedPreferences.getString(LoginFormconfig.NAME_SHARED_PREF, "Not Available");
    //    String user_mail = LoginForm.NAME;

     //   user.setText(" " + user_mail);
        if(LoginForm.login.equals("login"))
        {


            user.setText("" + user_mail);
        }
        else
        {

            user.setText("Hello guest");
            user.setTypeface(Typeface.SERIF);

        }



        Bundle extras = getIntent().getExtras();

        mstring = extras.getString("id");

        Toast.makeText(RegularWomens.this, ""+mstring, Toast.LENGTH_SHORT).show();

        if(getIntent().getExtras() != null) {
            mCategory = getIntent().getIntExtra(AppConstants.CATEGORIES_ID, Integer.parseInt(mstring));
        }
        mLvServiceCategories = (ListView) findViewById(R.id.listwomensregular);
        mLvServiceCategories.setOnItemClickListener(this);
        new GetJobs().execute();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(RegularWomens.this,WomenListItem.class);
        intent.putExtra(AppConstants.CATEGORIES_ID, id);
        startActivity(intent);
        Toast.makeText(RegularWomens.this, "welcome", Toast.LENGTH_SHORT).show();
    }

    private class GetJobs extends AsyncTask<Void, Void, ArrayList<SetGetSubCategoryWomen>> {
        private ProgressDialog mProgressDialog;

        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(RegularWomens.this, "", "Please wait...Loading",
                    false, true);
        }

        @Override
        protected ArrayList<SetGetSubCategoryWomen> doInBackground(Void... arg0) {
            String requestGenderCategory = new GenderCategory(Integer.valueOf(mstring)).getJson();
            String response = NetworkHandler.doPost(URLConstants.URL_DESPA_SUBCATEGORIES, requestGenderCategory);


            if(response != null) {

                // [{"sub_categories_id":"5","sub_categories_name":"Rebonding and Smoothening"},{"sub_categories_id":"6","sub_categories_name":"Keratin Spa Treatment"},{"sub_categories_id":"7","sub_categories_name":"Renew 'C'"},{"sub_categories_id":"8","sub_categories_name":"Hair Spa"},{"sub_categories_id":"10","sub_categories_name":"Scalp and Hair Treatment"},{"sub_categories_id":"9","sub_categories_name":"Hair Styles"},{"sub_categories_id":"11","sub_categories_name":"Keratin Treatment"},{"sub_categories_id":"12","sub_categories_name":"Global Coloring"},{"sub_categories_id":"15","sub_categories_name":"Heena"},{"sub_categories_id":"14","sub_categories_name":"Color Retouch"},{"sub_categories_id":"13","sub_categories_name":"Inoa Global Coloring"},{"sub_categories_id":"16","sub_categories_name":"Highlightning"}]


                try {

                    JSONArray jsonarray = new JSONArray(response);

                    // SetGet_main setGet_main=new SetGet_main();
                    for (int i = 0; i < jsonarray.length(); i++) {

                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        SetGetSubCategoryWomen setGet_main=new SetGetSubCategoryWomen();
                        setGet_main.setId(jsonobject.getString("sub_categories_id"));
                        setGet_main.setCategoryname_sub(jsonobject.getString("sub_categories_name"));

                        setlist.add(setGet_main);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return setlist;
        }

        @Override
        protected void onPostExecute(final ArrayList<SetGetSubCategoryWomen> result) {
            super.onPostExecute(result);
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            SubAdapterWomen adapter = new SubAdapterWomen(RegularWomens.this, result);

            mLvServiceCategories.setAdapter(adapter);

            mLvServiceCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String string = result.get(position).getId();
                    Intent intent = new Intent(RegularWomens.this,WomenListItem.class);
                    intent.putExtra("id",result.get(position).getId());

                    startActivity(intent);
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
        Intent i = new Intent(RegularWomens.this, CartActicity.class);
        startActivity(i);

    }

    public void backregularW(View view)
    {
        Intent i = new Intent(RegularWomens.this, Womens.class);
        startActivity(i);

    }

}


