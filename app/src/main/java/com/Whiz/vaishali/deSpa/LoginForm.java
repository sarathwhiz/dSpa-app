package com.Whiz.vaishali.deSpa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Whiz.vaishali.deSpa.adapter.ConnectionDetector;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class LoginForm extends AppCompatActivity implements View.OnClickListener {

    //   public static final String LOGIN_URL = "http://aabid.esy.es/deSpa/deSpa_Login.php";

    public static final String KEY_USERNAME = "user_mail";
    public static final String KEY_PASSWORD = "user_password";

    private String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private CallbackManager mCallbackManager;
    private EditText editTextUsername;
    private EditText editTextPassword;

    private TextView buttonsignup;
    private Button guestUser;


    public static String NAME;
    public static String IDD;

    private static long back_pressed;
    private String user_mail;
    private String user_password;
    CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    public static String login = "login";
    public static String guest = "guest";
    public static String Welcome = "welcome";

    private AppCompatButton buttonLogin;
    private boolean loggedIn = false;

    private EditText editPass;
    private ProgressDialog pro;

    private CheckBox checkBox;
    private TextView txtforgot;

    DatabaseClass db = new DatabaseClass(LoginForm.this);

    private ProgressDialog progressDialog = null;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_form);
        AppEventsLogger.activateApp(this);

        editPass = (EditText) findViewById(R.id.password);

        login = "";

//                checkBox = (CheckBox) findViewById(R.id.checkBox);
//
//
//                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                                // checkbox status is changed from uncheck to checked.
//                                if (!isChecked) {
//                                        // show password
//                                        editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                                } else {
//                                        // hide password
//                                        editPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                                }
//                        }
//                });

        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);

        buttonLogin = (AppCompatButton) findViewById(R.id.login);
        buttonsignup = (TextView) findViewById(R.id.signup);

        guestUser = (Button) findViewById(R.id.guest);

        txtforgot = (TextView) findViewById(R.id.forgotpassword);

        buttonLogin.setOnClickListener(this);
        buttonsignup.setOnClickListener(this);

        guestUser.setOnClickListener(this);

        txtforgot.setOnClickListener(this);

        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
             //   AccessToken.getCurrentAccessToken().getPermissions();
            }
        };
        // If the access token is available already assign it.
        //  accessToken = AccessToken.getCurrentAccessToken();

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                // App code
            }
        };
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Toast.makeText(LoginForm.this, "Welcome user", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginForm.this, TheDeSpaStudio.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Toast.makeText(LoginForm.this, "Wrong credential", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Toast.makeText(LoginForm.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }



    @Override
    protected void onResume() {
        super.onResume();

        //In onresume fetching value from sharedpreference

        SharedPreferences sharedPreferences = getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(LoginFormconfig.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if (loggedIn) {
            //We will start the Profile Activity
            Intent intent = new Intent(LoginForm.this, TheDeSpaStudio.class);
            login = "login";
            startActivity(intent);
            finish();
        }
    }

    private void login() {
        //Getting values from edit texts
        final String user_mail = editTextUsername.getText().toString().trim();
        final String user_password = editTextPassword.getText().toString().trim();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LoginFormconfig.LOGIN_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {




                        if(response.equals("failure")){


                            if (pDialog.isShowing()) {
                                pDialog.dismiss();

                TastyToast.makeText(getApplicationContext(), "Invalid username or password", TastyToast.LENGTH_SHORT,TastyToast.ERROR);

                            }

                        }

                         else {
                            //If the server response is not success
                            //Displaying an error message on toast


                            //If we are getting success from server
                            if (response.length() != 0) {
                                // Load the data from json
                                try {
                                    JSONArray jsonArray = new JSONArray(response);

                                    String name = jsonArray.getString(0);
                                    String id = jsonArray.getString(1);

                                    NAME=name;
                                    IDD=id;

                                    HashMap<String, String> jsonMap = new HashMap<String, String>();

                                    jsonMap.put(name, id);

                                    // here you put ean as key and nr as value
                                } catch (Exception e) {//Exception  //JSONException
                                    e.printStackTrace();
                                }

                                //Creating a shared preference
                                SharedPreferences sharedPreferences = LoginForm.this.getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                //Creating editor to store values to shared preferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                //Adding values to editor
                                editor.putBoolean(LoginFormconfig.LOGGEDIN_SHARED_PREF, true);
                                editor.putString(LoginFormconfig.EMAIL_SHARED_PREF, user_mail);
                                editor.putString(LoginFormconfig.NAME_SHARED_PREF, NAME);
                                editor.putString(LoginFormconfig.ID_SHARED_PREF, IDD);

                                //Saving values to editor
                                editor.commit();

                                //Starting profile activity

                                if (pDialog.isShowing()) {
                                    pDialog.dismiss();

                                 //   Toast.makeText(LoginForm.this, "" + response, Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(LoginForm.this, TheDeSpaStudio.class);
                                    login = "login";
                                    startActivity(intent);
                                    finish();
                                    db.deleteAll();

                                    editTextUsername.setText("");
                                    editTextPassword.setText("");
                                }
                            }

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(LoginFormconfig.KEY_EMAIL, user_mail);
                params.put(LoginFormconfig.KEY_PASSWORD, user_password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
//   login();

    @Override
    public void onClick(View v) {

        String user_email, user_pass;

        user_email = editTextUsername.getText().toString();
        user_pass = editTextPassword.getText().toString();

        if (v == buttonLogin) {

            if (user_email.equals("") && user_pass.equals("")) {

                TastyToast.makeText(LoginForm.this, "Please fill all the values", Toast.LENGTH_SHORT,TastyToast.WARNING);
            } else {
                if (user_email.matches(EMAIL_PATTERN)) {

                    if (user_pass.length() < 4) {
                        Toast.makeText(LoginForm.this, "user name minimum 4 letters", Toast.LENGTH_SHORT).show();
                        editTextPassword.setError("minimum 4 letters");
                    } else {
                        if (!new ConnectionDetector(LoginForm.this).isConnectingToInternet()) {

                            TastyToast.makeText(LoginForm.this, "Please check your internet Connection", Toast.LENGTH_SHORT,TastyToast.ERROR);
                        }
                        else
                        {
                            pDialog = ProgressDialog.show(LoginForm.this, "", "Please wait...",
                                    false, true);
                            login();

                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginForm.this, "invalid email", Toast.LENGTH_SHORT).show();
                    editTextUsername.setError("invalid email");
                }
            }
        }

        if (v == buttonsignup) {
            Intent i = new Intent(LoginForm.this, SignUp.class);
            startActivity(i);
            editTextUsername.setText("");
            editTextPassword.setText("");
        }

        if (v == txtforgot) {
            Intent i = new Intent(LoginForm.this, ForgotPassword.class);
            startActivity(i);
            editTextUsername.setText("");
            editTextPassword.setText("");
        }

        if (v == guestUser) {
            Intent i = new Intent(LoginForm.this, TheDeSpaStudio.class);
            guest = "guest";
            startActivity(i);
            db.deleteAll();

            editTextUsername.setText("");
            editTextPassword.setText("");
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        db.deleteAll();
        finish();

    }
}