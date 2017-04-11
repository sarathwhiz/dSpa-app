package com.Whiz.vaishali.deSpa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Whiz.vaishali.deSpa.adapter.ConnectionDetector;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
//    TextView a ;
//    EditText b ;
//    Button c ;

    Button button;
    Button back;
    TextView forgot;

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+");
    private ProgressDialog pDialog;
    private EditText mEdtmailid;

    public static final String ROOT_URL = "http://app.despastudio.com";

//  @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        getSupportActionBar().hide();

        String fontPath ="DancingScript_Regular.otf";


        forgot = (TextView)findViewById(R.id.fpassword);
        mEdtmailid= (EditText) findViewById(R.id.forgotemail);
        button = (Button) findViewById(R.id.submit);


               Typeface tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        forgot.setTypeface(tf);
        mEdtmailid.setTypeface(tf);
        button.setTypeface(tf);


        button.setOnClickListener(this);

    }
    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
    private void Forget_pass(){

        pDialog = new ProgressDialog(ForgotPassword.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);
        pDialog.show();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL).build();
        ForgotPasswordAPI api = adapter.create(ForgotPasswordAPI.class);
        //Defining the method insertuser of our interface
        api.passforgot(
                //Passing the values by getting it from editTexts
                mEdtmailid.getText().toString(),
                //Creating an anonymous callback
                new Callback<Response>() {

                    @Override
                    public void success(Response result, Response response) {
                        BufferedReader reader = null;

                        String output = "password Sent to your Email ID";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                      //  Toast.makeText(getApplicationContext(),output, Toast.LENGTH_LONG).show();

                        //Please enter a valid Email Address

                        if(output.equals("Please enter a valid Email Address")){

                            TastyToast.makeText(getApplicationContext(), ""+output, TastyToast.LENGTH_LONG,TastyToast.WARNING);

                            pDialog.dismiss();
                        }
                        else{
                            TastyToast.makeText(getApplicationContext(), ""+output, TastyToast.LENGTH_LONG,TastyToast.SUCCESS);


                            // Toast.makeText(ForgetPassword.this, output, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(ForgotPassword.this,LoginForm.class);
                            startActivity(i);
                            finish();

                        }



                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast

                        Toast.makeText(ForgotPassword.this, error.toString(),Toast.LENGTH_SHORT)
                                .show();
                        pDialog.dismiss();
                    }
                }
        );


    }

    @Override
    public void onClick(View v) {

        if (v==button)
        {String email=mEdtmailid.getText().toString();

            if(checkEmail(email)){
               // Toast.makeText(ForgotPassword.this,"Connecting...", Toast.LENGTH_SHORT).show();

                if (!new ConnectionDetector(ForgotPassword.this).isConnectingToInternet()) {

                    TastyToast.makeText(ForgotPassword.this, "Please check your internet Connection", Toast.LENGTH_SHORT,TastyToast.ERROR);
                }

                else {

                    Forget_pass();

                }

            }

            else{
              //  Toast.makeText(ForgotPassword.this,"Invalid Email Addresss", Toast.LENGTH_SHORT).show();
                TastyToast.makeText(getApplicationContext(), "Invalid Email Addresss", TastyToast.LENGTH_SHORT,TastyToast.WARNING);

        }



        }

    }
}