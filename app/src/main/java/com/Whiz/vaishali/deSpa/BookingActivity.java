package com.Whiz.vaishali.deSpa;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.Whiz.vaishali.deSpa.adapter.ConnectionDetector;
import com.Whiz.vaishali.deSpa.setget_category.SetGetCart;
import com.Whiz.vaishali.deSpa.webservice.WebService;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDatePicker, btnTimePicker;
    private TextView txtDate;
    private TextView txtTime;
    private EditText eName;
    private EditText eMobile;
    private EditText eEmail;
    private TextView user;

   public String user_name,user_mob,user_email,user_date,user_time,user_total,user_id;

    String jsondata;

    SimpleDateFormat format;

    java.sql.Time timeValue;

    private    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    ListView cust_lv;
    public static TextView total;
    double totalamount = 0.00;
    double totalamountswap = 0.00;


    JSONArray json;

    CustomAdapterBooking cust_adapter;
    ArrayList<SetGetCart> arr = new ArrayList<SetGetCart>();
    DatabaseClass db = new DatabaseClass(BookingActivity.this);



    private Button appointment;

    private int mYear, mMonth, mDay, mHour, mMinute, AM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        getSupportActionBar().hide();

        user = (TextView) findViewById(R.id.bookinguser);

        SharedPreferences sharedPreferences = getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String user_mail = sharedPreferences.getString(LoginFormconfig.NAME_SHARED_PREF, "Not Available");
        user_id = sharedPreferences.getString(LoginFormconfig.ID_SHARED_PREF, "Not Available here");
       // String user_mail = LoginForm.NAME;

        user.setText(" " + user_mail);

        if(LoginForm.login.equals("login")) {

            user.setText("" + user_mail);

        }else{

            user.setText("Hello guest");
            user.setTypeface(Typeface.SERIF);

        }

        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);

        eName = (EditText) findViewById(R.id.bookingname);
        eMobile = (EditText) findViewById(R.id.bookingmobile);
        eEmail = (EditText) findViewById(R.id.bookingemail);

        appointment = (Button) findViewById(R.id.appointment);

        txtDate = (TextView) findViewById(R.id.in_date);
        txtTime = (TextView) findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

         appointment.setOnClickListener(this);

        cust_lv = (ListView) findViewById(R.id.cust_listView2);

        total = (TextView) findViewById(R.id.bookingtotal);
        //  cust_adapter = new CustomAdapterCart(BookingActivity.this,arr);
        //  cust_lv.setAdapter(cust_adapter);
        cust_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                bookingrefresh();

            }
        });

        totalamountswap=0;

        // Always call the superclass method first
        arr = db.getContacts();

        for (int i = 0; i < arr.size(); i++) {
            JSONObject jso=new JSONObject();
            JSONArray json=new JSONArray();

            totalamount = Double.parseDouble(arr.get(i).getPrice_cart());
            totalamountswap += totalamount;
        }

        BookingActivity.total.setText("" + totalamountswap); //"₹ "+

        cust_adapter = new CustomAdapterBooking(BookingActivity.this, arr);

        cust_lv.setAdapter(cust_adapter);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void bookingrefresh(){

        totalamountswap=0;

        // Always call the superclass method first
        arr = db.getContacts();

        for (int i = 0; i < arr.size(); i++) {
            JSONObject jso=new JSONObject();
            JSONArray json=new JSONArray();

            totalamount = Double.parseDouble(arr.get(i).getPrice_cart());
            totalamountswap += totalamount;
        }

        BookingActivity.total.setText("" + totalamountswap); //"₹ "+

        cust_adapter = new CustomAdapterBooking(BookingActivity.this, arr);

        cust_lv.setAdapter(cust_adapter);
    }



    @Override
    public void onClick(View v) {



        user_name=eName.getText().toString().trim();
        user_mob=eMobile.getText().toString();
        user_email=eEmail.getText().toString();
        user_date=txtDate.getText().toString();
        user_time=txtTime.getText().toString();
        user_total=total.getText().toString();


        if (v == appointment) {

            if( user_name.equals("") && user_mob.equals("") && user_email.equals("") &&user_date.equals("") || user_time.equals("") || user_total.equals("") )
            {
                TastyToast.makeText(getApplicationContext(),"Please fill all the value" ,Toast.LENGTH_SHORT,TastyToast.WARNING);
            }

            else{

                if (user_name.length() < 3 || user_name.length() > 15) {

                    Toast.makeText(getApplicationContext(), "minimum 3-15 letters", Toast.LENGTH_SHORT).show();

                    eName.setError("minimum 3-15 letters");
                }

                else
                {

                    if(user_mob.length() == 10 || user_mob.length() == 11)
                    {

                        if(user_email.matches(EMAIL_PATTERN)){


                            if(user_date.equals("")){

                                Toast.makeText(getApplicationContext(), "please select date", Toast.LENGTH_SHORT).show();
                                txtDate.setError("please select date");
                            }

                            else{

                                if(user_time.equals(""))
                                {
                                    Toast.makeText(getApplicationContext(), "please select time", Toast.LENGTH_SHORT).show();
                                    txtTime.setError("please select time");
                                }
                                else{

                                    if(user_total.equals("0.0"))  //₹ 0.0
                                    {

                                        Toast.makeText(getApplicationContext(), "please add services to cart", Toast.LENGTH_SHORT).show();
                                    }
                                    else{

                                        if (!new ConnectionDetector(BookingActivity.this).isConnectingToInternet()) {

                                            TastyToast.makeText(getApplicationContext(), "Please check your internet Connection", TastyToast.LENGTH_SHORT,TastyToast.ERROR);
                                    }
                                        else{

                                            new AsyncTaskWS().execute();

                                        }
                                    }

                                }

                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "invalid email", Toast.LENGTH_SHORT).show();
                            eEmail.setError("invalid email");
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "invalid number", Toast.LENGTH_SHORT).show();
                        eMobile.setError("invalid number");
                    }
                }
            }

        }

        if (v == btnDatePicker) {



            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {



            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            boolean isPM = (hourOfDay >= 12);
                            txtTime.setText(String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
                        }
                    },
                    mHour, mMinute,
                    DateFormat.is24HourFormat(BookingActivity.this)
            );
            timePickerDialog.show();
        }
    }

    private ProgressDialog progressDialog = null;
    private ProgressDialog pDialog;
    private class AsyncTaskWS extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = ProgressDialog.show(BookingActivity.this, "", "Please wait...",
                    false, true);
        }

        @Override
        protected String doInBackground(String... strings) {

            JSONArray jsonArray = new JSONArray();

            String resultSuccess = null;

            try {

                String url = "http://app.despastudio.com/appointment.php";

                JSONObject jsonObjectone = new JSONObject();

                jsonObjectone.put("name",user_name);
                jsonObjectone.put("mobile",user_mob);
                jsonObjectone.put("email",user_email);
                jsonObjectone.put("req_date",user_date);
                jsonObjectone.put("req_time",user_time);
                jsonObjectone.put("user_id",user_id);
                jsonObjectone.put("total",user_total);
                jsonArray.put(jsonObjectone);

                JSONArray jsonArray1=new JSONArray();


                for(int i=0;i<=arr.size();i++){

                    try {
                        JSONObject jobject = new JSONObject();
                        jobject.put("items_name",arr.get(i).getName_cart());

                        jobject.put("price",arr.get(i).getPrice_cart());
                        jsonArray1.put(jobject);
                    }

                    catch(Exception e)
                    {
               e.printStackTrace();
                    }
                }
                JSONObject jsonObjectfinal = new JSONObject();
                jsonObjectfinal.put("details",jsonArray);
                jsonObjectfinal.put("cart",jsonArray1);

                jsondata= jsonObjectfinal.toString();

                String res = new WebService().postRequest(url,jsonObjectfinal);


                Toast.makeText(BookingActivity.this, ""+res, Toast.LENGTH_SHORT).show();

            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return resultSuccess;
        }

        @Override
        protected void onPostExecute(String result) {

            if (pDialog != null) {
                pDialog.dismiss();


                TastyToast.makeText(BookingActivity.this, "Your appointment has been booked successfully", Toast.LENGTH_SHORT,TastyToast.SUCCESS);



                Intent launchNextActivity;
                launchNextActivity = new Intent(BookingActivity.this, TheDeSpaStudio.class);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(launchNextActivity);

            }


        }

    }

}