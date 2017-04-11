package com.Whiz.vaishali.deSpa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Whiz.vaishali.deSpa.adapter.ConnectionDetector;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    public static final String ROOT_URL = "http://app.despastudio.com";
private ProgressDialog pDialog;
    private EditText mEdtName;
    private EditText mEdtMail;
    private EditText mEdtMobileNumber;
    private EditText mEdtPassword;
    private Button mbtncreate;
    private Button mbtncancle;

    private String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mEdtName=(EditText)findViewById(R.id.signupname);
        mEdtMobileNumber=(EditText)findViewById(R.id.signupemail);
        mEdtMail=(EditText)findViewById(R.id.signupcontact);
        mEdtPassword=(EditText)findViewById(R.id.signuppassword);

        mbtncreate=(Button)findViewById(R.id.createaccount);
        mbtncancle=(Button)findViewById(R.id.cancel);

        mbtncreate.setOnClickListener(this);
        mbtncancle.setOnClickListener(this);

    }

    private void insertUser(){
        pDialog = new ProgressDialog(SignUp.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);
        pDialog.show();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter
                     //Creating object for our interface
        SignUpAPI api = adapter.create(SignUpAPI.class);
        //Defining the method insertuser of our interface
        api.insertUser(


                //Passing the values by getting it from editTexts
                mEdtName.getText().toString(),
                mEdtMail.getText().toString(),
                mEdtMobileNumber.getText().toString(),
                mEdtPassword.getText().toString(),



                //Creating an anonymous callback
                new Callback<Response>() {

                    @Override
                    public void success(Response result, Response response) {
                        BufferedReader reader = null;
                        String output = "";


                        // startActivity(new Intent(SignUpActivity.this, WelcomeUser.class));
                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            //Reading the output in the string
                            output = reader.readLine();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if(output.equals("Registered successfully")){

                            Intent i = new Intent(SignUp.this,LoginForm.class);
//                            i.putExtra("user_name","user_name");
//                            i.putExtra("user_password","user_password");
                            startActivity(i);
                            finish();

                        }
                        else if(output.equals("email or mobile number already exist")) {
                            startActivity(new Intent(SignUp.this, LoginForm.class));
                        }


                        //Displaying the output as a toast
                        Toast.makeText(SignUp.this, output, Toast.LENGTH_SHORT).show();
                        //  startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
//                        startActivity(new Intent(SignUpActivity.this, WelcomeUser.class));
                        if (pDialog.isShowing())
                            pDialog.dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(SignUp.this, "Network Slow... Please Try again",Toast
                                .LENGTH_SHORT)
                                .show();
                        startActivity(new Intent(SignUp.this,LoginForm.class));
                    }
                }
        );
    }
//insertUser();
    @Override
    public void onClick(View v) {

        String user_string,email_string,mobile_string,password_strng;
        user_string=mEdtName.getText().toString();
        email_string=mEdtMobileNumber.getText().toString();//email
        mobile_string=mEdtMail.getText().toString();       //mobile
        password_strng=mEdtPassword.getText().toString();

          if(v==mbtncreate)


            {
                if( user_string.equals("") && email_string.equals("") && mobile_string.equals("") &&password_strng.equals("") || user_string.equals("") || email_string.equals("") || mobile_string.equals("") || password_strng.equals("") )

                {
                    TastyToast.makeText(getApplicationContext(),"Please fill allthe values" ,Toast.LENGTH_SHORT,TastyToast.WARNING);
                }
                else
                {
                    if (user_string.length() < 3 || user_string.length() > 15) {

                        Toast.makeText(SignUp.this, "minimum 3 letters", Toast.LENGTH_SHORT).show();

                        mEdtName.setError("minimum 3 letters");
                    }
                    else
                    {
                        if (email_string.matches(EMAIL_PATTERN)) {

                            if(mobile_string.length() == 10 || mobile_string.length() == 11){

                                if(password_strng.length()<4)
                                {
                                    Toast.makeText(SignUp.this, "user name minimum 4 letters", Toast.LENGTH_SHORT).show();
                                    mEdtPassword.setError("minimum 4 letters");
                                }
                                else
                                {

                                    if (!new ConnectionDetector(SignUp.this).isConnectingToInternet()) {

                                      //  Toast.makeText(SignUp.this, "Please check your internet Connection", Toast.LENGTH_SHORT).show();
                                        TastyToast.makeText(getApplicationContext(), "Please check your internet Connection", TastyToast.LENGTH_SHORT,TastyToast.ERROR);

                                    }
                                    else{
                                       // Toast.makeText(SignUp.this, "success", Toast.LENGTH_SHORT).show();

                                        insertUser();

                                    }


                                }
                            }

                            else{

                                Toast.makeText(SignUp.this, "invalid number", Toast.LENGTH_SHORT).show();
                                mEdtMail.setError("invalid number");
                            }

                        }
                        else
                        {
                            Toast.makeText(SignUp.this, "invalid email", Toast.LENGTH_SHORT).show();
                            mEdtMobileNumber.setError("invalid email");

                        }

                    }
                }

              }

        if(v==mbtncancle)
        {
//            Intent i = new Intent(SignUp.this,LoginForm.class);
//            startActivity(i);
//            finish();

            Intent launchNextActivity;
            launchNextActivity = new Intent(SignUp.this, LoginForm.class);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(launchNextActivity);
        }
    }

}
