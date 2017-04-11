package com.Whiz.vaishali.deSpa;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
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

@TargetApi(Build.VERSION_CODES.HONEYCOMB)

public class Feedback extends Fragment implements View.OnClickListener {
    public static final String ROOT_URL = "http://aabid.esy.es/deSpa";

    private TextView a;
    private TextView name;
    private TextView c;
    private TextView dd;

    private TextView ff;
    private TextView gg;
    private ProgressDialog pDialog;
    private EditText edtName;
    private EditText edtContact;
    private EditText edtEmail;
    private EditText edtComments;

    private TextView ratetext;
    String ratedValue;
    private RatingBar ratebar;

    private String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


//    private RatingBar rating;
//    private TextView ratingvalue;

    private Button btnSubmit;

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feedback, container, false);


        edtName = (EditText) view.findViewById(R.id.feedname);
        edtContact = (EditText) view.findViewById(R.id.feedmob);
        edtEmail = (EditText) view.findViewById(R.id.feedemail);
        edtComments = (EditText) view.findViewById(R.id.feedcomment);
        btnSubmit= (Button) view.findViewById(R.id.feedsubmit);

        ratetext=(TextView)view.findViewById(R.id.ratingvalue);

        ratebar=(RatingBar)view.findViewById(R.id.ratingBar);

        a = (TextView) view.findViewById(R.id.feed);
        Typeface font1 = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        a.setTypeface(font1);

        name = (TextView) view.findViewById(R.id.name);
        Typeface font2 = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf");
        name.setTypeface(font2);

       c = (TextView) view.findViewById(R.id.mob);
        Typeface font3 = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        c.setTypeface(font3);

        dd = (TextView) view.findViewById(R.id.email);
        Typeface font4 = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        dd.setTypeface(font4);

         ff = (TextView) view.findViewById(R.id.comment);
        Typeface font5 = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        ff.setTypeface(font5);

         gg = (TextView) view.findViewById(R.id.rate);
        Typeface font6 = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        gg.setTypeface(font6);

        Typeface font7 = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        btnSubmit.setTypeface(font7);


        btnSubmit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        String user_string,email_string,mobile_string,password_strng,textrate;
        user_string=edtName.getText().toString();
        mobile_string=edtContact.getText().toString();//email
        email_string=edtEmail.getText().toString();       //mobile
        password_strng=edtComments.getText().toString().trim();
        textrate=ratetext.getText().toString();


        if(v==btnSubmit)
        {
            if( user_string.equals("") && email_string.equals("") && mobile_string.equals("") &&password_strng.equals("") || user_string.equals("") || email_string.equals("") || mobile_string.equals("") || password_strng.equals("") || textrate.equals("") )

            {
                TastyToast.makeText(getActivity(),"Please fill allthe values" ,Toast.LENGTH_SHORT,TastyToast.WARNING);


            }
            else
            {
                if (user_string.length() < 3 || user_string.length() > 15) {

                    Toast.makeText(getActivity(), "minimum 3 letters", Toast.LENGTH_SHORT).show();

                    edtName.setError("minimum 3 letters");
                }
                else
                {
                    if (email_string.matches(EMAIL_PATTERN)) {

                        if(mobile_string.length() == 10 || mobile_string.length() == 11){

                            if(password_strng.length()<10)
                            {
                                Toast.makeText(getActivity(), " minimum 10 letters", Toast.LENGTH_SHORT).show();
                                edtComments.setError("minimum 10 letters");
                            }
                            else
                            {


                                if(ratedValue.equals("0.0")){

                                    Toast.makeText(getActivity(), "Please rate us", Toast.LENGTH_SHORT).show();
                                }
                                else{

                                    if (!new ConnectionDetector(getActivity()).isConnectingToInternet()) {

                                        TastyToast.makeText(getActivity(), "Please check your internet Connection", Toast.LENGTH_SHORT,TastyToast.ERROR);

                                    }
                                    else{
                                        sendfeedback();

                                    }



                                }

                            }
                        }

                        else{

                            Toast.makeText(getActivity(), "invalid number", Toast.LENGTH_SHORT).show();
                            edtContact.setError("invalid number");
                        }

                    }
                    else
                    {
                        Toast.makeText(getActivity(), "invalid email", Toast.LENGTH_SHORT).show();
                        edtEmail.setError("invalid email");

                    }

                }
            }
        }

    }

    private void sendfeedback() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);
        pDialog.show();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter
        //Creating object for our interface
        FeedbackAPI api = adapter.create(FeedbackAPI.class);
        //Defining the method insertuser of our interface
        api.insertcomments(

                //Passing the values by getting it from editTexts
                edtName.getText().toString(),
                edtContact.getText().toString(),
                edtEmail.getText().toString(),
                edtComments.getText().toString(),
                ratetext.getText().toString(),

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
                        Toast.makeText(getActivity(), output, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(),TheDeSpaStudio.class));
                        getActivity().finish();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity(),error.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
        );
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