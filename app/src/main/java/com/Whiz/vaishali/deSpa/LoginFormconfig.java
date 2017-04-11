package com.Whiz.vaishali.deSpa;

/**
 * Created by ACER on 12/9/2016.
 */
public class LoginFormconfig {

    //URL to our login.php file
    public static final String LOGIN_URL = "http://app.despastudio.com/deSpa_Login.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "user_mail";
    public static final String KEY_PASSWORD = "user_password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "Welcome";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    // //This would be used to store the USER NAME of current logged in user

    public static final String NAME_SHARED_PREF = "user";

    public static final String ID_SHARED_PREF = "IDD";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";


}
