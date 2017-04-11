package com.Whiz.vaishali.deSpa;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class HomePage extends Fragment  {
        ImageView img;
        ImageView img2;
        Animation fade_in, fade_out;
        ViewFlipper viewFlipper;


        String idString;
        ImageButton btnlogout;

      //  DatabaseClass db =new DatabaseClass(getActivity());
      //  private TextView textView;

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

                View view = inflater.inflate(R.layout.home_page, container, false);


                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                String user_mail = sharedPreferences.getString(LoginFormconfig.EMAIL_SHARED_PREF,"Not Available");

//                Bundle bundle = getActivity().getIntent().getExtras();
//                idString= bundle.getString("id");
//
//                if(idString.equals("1")) {
//
//                        btnlogout.setVisibility(View.VISIBLE);
//
//                } if(idString.equals("2")) {
//
//                        btnlogout.setVisibility(View.GONE);
//                }
//
//
//                btnlogout.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                                logout();
//
//                        }
//                });


                viewFlipper = (ViewFlipper) view.findViewById(R.id.bckgrndViewFlipper1);
//                fade_in = AnimationUtils.loadAnimation(HomePage.this, android.R.anim.fade_in);
//                fade_out = AnimationUtils.loadAnimation(HomePage.this, android.R.anim.fade_out);
                viewFlipper.setInAnimation(fade_in);
                viewFlipper.setOutAnimation(fade_out);
//sets auto flipping
                viewFlipper.setAutoStart(true);
                viewFlipper.setFlipInterval(3000);
                viewFlipper.startFlipping();

                TextView txt1 = (TextView) view.findViewById(R.id.men);
                Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"AlexBrush-Regular.ttf" );
                txt1.setTypeface(font);

                TextView txt2 = (TextView) view.findViewById(R.id.women);
                Typeface font1 = Typeface.createFromAsset(getActivity().getAssets(),"AlexBrush-Regular.ttf" );
                txt2.setTypeface(font1);

                img=(ImageView) view.findViewById(R.id.mens);
                img2=(ImageView) view.findViewById(R.id.womens);

                img.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), Mens.class);

                                startActivity(intent);
                                ((Activity) getActivity()).overridePendingTransition(0,0);
              //                  getActivity().finish();

                        }
                });


                img2.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                                Intent intent = new Intent(getActivity(), Womens.class);

                                startActivity(intent);
                                ((Activity) getActivity()).overridePendingTransition(0,0);
                        //        getActivity().finish();
                        }
                });

                return view;
        }

        private void logout(){
                //Creating an alert dialog to confirm logout
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage("Are you sure you want to logout?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                        //Getting out sharedpreferences
                                        SharedPreferences preferences = getActivity().getSharedPreferences(LoginFormconfig.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                                        //Getting editor
                                        SharedPreferences.Editor editor = preferences.edit();

                                        //Puting the value false for loggedin
                                        editor.putBoolean(LoginFormconfig.LOGGEDIN_SHARED_PREF, false);

                                        //Putting blank value to email
                                        editor.putString(LoginFormconfig.EMAIL_SHARED_PREF, "");

                                        //Saving the sharedpreferences
                                        editor.commit();

                                        //Starting login activity
//                                        Intent intent = new Intent(getActivity(), LoginForm.class);
//
//                                        startActivity(intent);
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
//
//        @Override
//        public void onResume() {
//
//                super.onResume();
//
//                getView().setFocusableInTouchMode(true);
//                getView().requestFocus();
//                getView().setOnKeyListener(new View.OnKeyListener() {
//                        @Override
//                        public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//                                if (event.getAction() == KeyEvent.ACTION_DOWN) {
//                                        if (keyCode == KeyEvent.KEYCODE_BACK) {
//                                                getActivity().finish();
////                                                Intent intent = new Intent(getActivity(), LoginForm.class);
////                                                startActivity(intent);
//
//                                            //    db.deleteAll();
//                                                getActivity().finish();
//
//                                                return true;
//                                        }
//                                }
//                                return false;
//                        }
//                });
//        }

}




