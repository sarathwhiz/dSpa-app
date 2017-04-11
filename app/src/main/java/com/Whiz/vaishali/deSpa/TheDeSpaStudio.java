package com.Whiz.vaishali.deSpa;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class TheDeSpaStudio extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


//    Animation fade_in, fade_out;
//    ViewFlipper viewFlipper;

    private TextView textView;
    private ImageButton btnlogout;
    public static String logString;

    FloatingActionButton fab;


    DatabaseClass db =new DatabaseClass(TheDeSpaStudio.this);

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_de_spa_studio);

        textView = (TextView)findViewById(R.id.textdespa);

//        btnlogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(TheDeSpaStudio.this,CartActicity.class);
//                startActivity(i);
//            }
//        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fr = new HomePage();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.flcontent, fr);
        transaction.commit();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {



            SharedPreferences sharedPreferences = getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            String user_mail = sharedPreferences.getString(LoginFormconfig.EMAIL_SHARED_PREF, "Not Available");

//            textView.setText("  User : " + user_mail);


            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Welcome to your cart", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Fragment fr = new Cart();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.addToBackStack(null);
//                transaction.replace(R.id.flcontent, fr);
//                transaction.commit();
                Intent i = new Intent(TheDeSpaStudio.this,CartActicity.class);
                startActivity(i);

            }

        });

        SharedPreferences sharedPreferences = getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String user_name = sharedPreferences.getString(LoginFormconfig.EMAIL_SHARED_PREF, "Not Available");
      //  String user_name = sharedPreferences.getString(LoginFormconfig.NAME_SHARED_PREF, "Not here");
      //  String user_name = LoginForm.NAME;


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);
        textView = (TextView)header.findViewById(R.id.textdespa);
 // textView.setText("" + user_mail);

//       if(LoginForm.Welcome.equals("welcome")) {
//
//           textView.setText("" + user_mail);
//       }
//        else if(LoginForm.guest.equals("guest")) {
//
//           textView.setText("Guest");
//       }

        btnlogout =(ImageButton)header.findViewById(R.id.homelogout);

//        Bundle bundle = getIntent().getExtras();
//        logString= bundle.getString("idl");

        if(LoginForm.login.equals("login")) {

            btnlogout.setVisibility(View.VISIBLE);
            textView.setText("" + user_name);

        }else
        {
            if(LoginForm.guest.equals("guest")){

                btnlogout.setVisibility(View.INVISIBLE);
                textView.setText(" Guest");
            }
        }

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                db.deleteAll();

            }
        });

    }

//    @Override
//    public void onBackPressed() {
////        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
////        if (drawer.isDrawerOpen(GravityCompat.START)) {
////            drawer.closeDrawer(GravityCompat.START);
////        } else {
////            super.onBackPressed();
////        }
//finish();
//
//    }


//    public void next (View view) {
//        Intent i = new Intent(TheDeSpaStudio.this, LoginForm.class);
//        startActivity(i);
//    }

    //

    //##############################################################################################################


//    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//    navigationView.setNavigationItemSelectedListener(this);
//    View header=navigationView.getHeaderView(0);
///*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
//    name = (TextView)header.findViewById(R.id.username);
//
//    name.setText(personName);

    //################################################################################
    private void logout(){

        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(LoginFormconfig.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(LoginFormconfig.EMAIL_SHARED_PREF, "");
                        editor.putString(LoginFormconfig.NAME_SHARED_PREF, "");
                        editor.putString(LoginFormconfig.ID_SHARED_PREF, "");


                        //Saving the sharedpreferences

                        editor.commit();

                     //   preferences.editor().remove(PREFERENCES_LOGIN).apply();

                        //Starting login activity
                        Intent launchNextActivity;
                        launchNextActivity = new Intent(TheDeSpaStudio.this, LoginForm.class);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        db.deleteAll();
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(launchNextActivity);

//                        Intent intent = new Intent(TheDeSpaStudio.this, LoginForm.class);
//                        db.deleteAll();
//                        startActivity(intent);
//                                finish();

                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

 //###########################################################################

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            Fragment fr = new Cart();
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.addToBackStack(null);
//            transaction.replace(R.id.flcontent, fr);
//            transaction.commit();
            Intent i = new Intent(TheDeSpaStudio.this,CartActicity.class);
            startActivity(i);
        }

//        else if (id == R.id.action_exit) {
////            Intent i = new Intent(this, LoginForm.class);
////            startActivity(i);
//        }

//        else if(id == R.id.menuLogout){
//
//            logout();
//            db.deleteAll();
//        }

        return super.onOptionsItemSelected(item);

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Fragment fr = new HomePage();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.flcontent, fr);
            transaction.commit();

        } else if (id == R.id.nav_gallery) {

            Intent i = new Intent(TheDeSpaStudio.this,Photo.class);
            startActivity(i);

        } else if (id == R.id.nav_cart) {

//            Fragment fr = new Cart();
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.addToBackStack(null);
//            transaction.replace(R.id.flcontent, fr);
//            transaction.commit();
            Intent i = new Intent(TheDeSpaStudio.this,CartActicity.class);
            startActivity(i);

        }
        else if (id == R.id.nav_profile) {

            Fragment fr = new MyApointmentFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.flcontent, fr);
            transaction.commit();
        }

        else if (id == R.id.nav_aboutus) {
            Fragment fr = new AboutUs();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.flcontent, fr);
            transaction.commit();


        } else if (id == R.id.nav_contactus) {
            Fragment fr = new ContactUs();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.flcontent, fr);
            transaction.commit();

        } else if (id == R.id.nav_feedback) {

//            Fragment fr = new Feedback();
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.addToBackStack(null);
//            transaction.replace(R.id.flcontent, fr);
//            transaction.commit();

         //   fab.setVisibility(View.GONE);
            Intent i = new Intent(TheDeSpaStudio.this,Suggestion.class);
            startActivity(i);

        } else if (id == R.id.nav_offer) {
            Fragment fr = new SpecialOffers();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.flcontent, fr);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void onBackPressed() {

        db.deleteAll();
//        Intent i = new Intent(TheDeSpaStudio.this, LoginForm.class);
//        startActivity(i);
        finish();

    }

//
//    @Override
//    public void onResume() {
//
//        super.onResume();
//
//        getView().setFocusableInTouchMode(true);
//        getView().requestFocus();
//        getView().setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//                if (event.getAction() == KeyEvent.ACTION_DOWN) {
//                    if (keyCode == KeyEvent.KEYCODE_BACK) {
//                        finish();
////                                                Intent intent = new Intent(getActivity(), LoginForm.class);
////                                                startActivity(intent);
//
//                           db.deleteAll();
//                      finish();
//
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
//    }


}
